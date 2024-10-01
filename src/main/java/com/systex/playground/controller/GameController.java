package com.systex.playground.controller;

import com.systex.playground.model.GuessGame;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class GameController {

    @GetMapping("/guess")
    public String showGuessPage(HttpSession session, Model model) {
        GuessGame game = (GuessGame) session.getAttribute("guessGame");
        if (game == null) {
            game = new GuessGame(10, 3); 
            session.setAttribute("guessGame", game);
        }
        model.addAttribute("remains", game.getRemains());
        return "guess"; 
    }

    @PostMapping("/guess")
    public String processGuess(@RequestParam("guessNumber") String guessNumberStr, HttpSession session, Model model) {
        GuessGame game = (GuessGame) session.getAttribute("guessGame");
        if (game == null) {
            game = new GuessGame(10, 3);
            session.setAttribute("guessGame", game);
        }

        try {
            int guessNumber = Integer.parseInt(guessNumberStr);
            boolean isCorrect = game.guess(guessNumber); 

            if (isCorrect) {
                session.removeAttribute("guessGame");
                session.removeAttribute("hint");
                return "youWin";
            } else if (game.getRemains() <= 0) {
                session.removeAttribute("guessGame");
                session.removeAttribute("hint");
                return "youLose";
            } else {
                String hint = game.getHint(guessNumber);
                session.setAttribute("hint", hint);
                model.addAttribute("remains", game.getRemains());
                return "guess";
            }
        } catch (NumberFormatException e) {
            return "redirect:/guess?error=invalid";
        }
    }
}
