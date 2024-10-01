package com.systex.playground.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class LotteryController {

    @GetMapping("/lottery")
    public String showLotteryPage() {
        return "lottery"; 
    }

    @PostMapping("/lottery")
    public String generateLotteryNumbers(
            @RequestParam(value = "excludeNumbers", required = false) String excludeNumbersStr,
            @RequestParam(value = "loopCount", required = false) String loopCountStr,
            HttpServletRequest request,
            Model model) {

        List<Integer> excludeNumbers = new ArrayList<>();
        int loopCount = 1;

        boolean hasError = false;
        StringBuilder errorMessage = new StringBuilder();

        try {
            loopCount = Integer.parseInt(loopCountStr);
            if (loopCount <= 0) {
                hasError = true;
                errorMessage.append("Loop Count must be a positive integer.<br>");
            }
        } catch (NumberFormatException e) {
            hasError = true;
            errorMessage.append("Loop Count must be a valid integer.<br>");
        }

        if (excludeNumbersStr != null && !excludeNumbersStr.trim().isEmpty()) {
            String[] numbers = excludeNumbersStr.trim().split("\\s+");
            for (String numStr : numbers) {
                try {
                    int num = Integer.parseInt(numStr);
                    if (num < 1 || num > 49) {
                        hasError = true;
                        errorMessage.append("Excluded numbers must be between 1 and 49.<br>");
                        break;
                    }
                    excludeNumbers.add(num);
                } catch (NumberFormatException e) {
                    hasError = true;
                    errorMessage.append("Excluded numbers must be valid integers separated by spaces.<br>");
                    break; 
                }
            }
        }

        if (hasError) {
            model.addAttribute("error", errorMessage.toString());
            return "lottery"; 
        }

        List<String> results = new ArrayList<>();

        for (int i = 0; i < loopCount; i++) {
            Set<Integer> lotteryNumbers = generateLotteryNumbers(excludeNumbers);
            results.add(formatLotteryNumbers(lotteryNumbers));
        }

        model.addAttribute("result", String.join("\n", results));

        return "lotteryResult"; 
    }

    private Set<Integer> generateLotteryNumbers(List<Integer> excludeNumbers) {
        Set<Integer> lotteryNumbers = new TreeSet<>();
        Random random = new Random();

        while (lotteryNumbers.size() < 6) {
            int number = random.nextInt(49) + 1;
            if (!excludeNumbers.contains(number)) {
                lotteryNumbers.add(number);
            }
        }

        return lotteryNumbers;
    }

    private String formatLotteryNumbers(Set<Integer> numbers) {
        StringBuilder sb = new StringBuilder();
        for (int num : numbers) {
            sb.append(String.format("%02d ", num));
        }
        return sb.toString().trim();
    }
}
