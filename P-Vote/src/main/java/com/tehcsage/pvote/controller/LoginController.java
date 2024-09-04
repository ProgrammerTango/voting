package com.tehcsage.pvote.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tehcsage.pvote.model.CandidateData;
import com.tehcsage.pvote.service.CandidateDataService;
//import com.tehcsage.pvote.service.UserService;
import com.tehcsage.pvote.util.DatabaseUtil;
import com.tehcsage.pvote.util.UserUtil;

@Controller
public class LoginController {
//	
//	@Autowired
//	UserService userService;
	
	@Autowired
    private CandidateDataService dataService;
	
	@Autowired
	DatabaseUtil databaseUtil;
	
    @GetMapping("/login")
    public String login() {
        return "login"; // Refers to the login.html template
    }
    
    @GetMapping("/candidates")
    public String showCandidates() {
        return "candidates"; // Refers to the candidates.html template
    }
    
    @GetMapping("/instructions")
    public String getInstructions() {
    	try {
			if(databaseUtil.checkVoted(UserUtil.getUsername())) {
				return "voting-thanks";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "instructions"; // Refers to the voter.html template
    }
    
    @GetMapping("/home")
    public String home() {
    	try {
			if(databaseUtil.checkVoted(UserUtil.getUsername())) {
				return "voting-thanks";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "candidates2"; // Refers to the voter.html template
    }
    
    @GetMapping("/checkUser")
    public ResponseEntity<String> checkUserVerified(@RequestParam String userName) throws SQLException {
        if (databaseUtil.checkUser(userName)) {
            if (databaseUtil.checkUserActive(userName)) {
                return ResponseEntity.ok("User is active");
            } else {
                try {
					databaseUtil.generateAndSendPass(userName);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("An issue occurred with sedning the email.");
				}
                return ResponseEntity.status(HttpStatus.OK).body("User is inactive, password generated and sent");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
    
    @GetMapping("/data")
    public ResponseEntity<List<CandidateData>> getData() {
    	List<CandidateData> data = dataService.getDataFromTables();
        return ResponseEntity.ok(data);
    }
    
    @PostMapping("/castVote")
    public String castVote(@RequestParam Map<String, String> jMap) {
    	try {
			databaseUtil.castUserVote(UserUtil.getUsername(), jMap.get("Chairman"), jMap.get("Officials"), jMap.get("Secretary"), jMap.get("Treasurer"));
			return "redirect:/home";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return "redirect:/home";
    }
    
    @GetMapping("/showThanks")
    public String showThanks() {
    	return "voting-thanks-2";
    }
}
