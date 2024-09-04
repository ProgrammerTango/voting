//package com.tehcsage.pvote.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.tehcsage.pvote.entity.RealUser;
//import com.tehcsage.pvote.entity.User;
//import com.tehcsage.pvote.repository.RealUserRepository;
//import com.tehcsage.pvote.repository.UserRepository;
//import com.tehcsage.pvote.util.UserUtil;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Service
//public class UserService {
//
//	@Autowired
//    private UserRepository userRepository;
//
//	@Autowired
//    private RealUserRepository realUserRepository;
//	
//	public void generatePassword(String username) {
//    	User user = userRepository.findByUsername(username);
//    	
//    	if (user == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//    	
//    	String password = UserUtil.generateRandomString(4);
//    	
//    	if(user.getPassword() == null) {
//    		user.setPassword(password);
//    		userRepository.save(user);
//    	}
//	}
//	
//	public void generatePass(String username) {
//		
//    	RealUser user = realUserRepository.findByUsername(username);
//    	
//    	if (user == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//    	
//    	String password = UserUtil.generateRandomString(4);
//    	
//    	if(user.getPassword() == null) {
//    		user.setPassword(password);
//    		user.setIs_active('Y');
//    		log.info("Saving user.....");
//    		realUserRepository.save(user);
//    		UserUtil.sendEmail(username, "Pvote Password", "The following is your password: \n " + password + "\nRegards.");
//    	}
//	}
//	
//	public void castVote(String username, int chairVote, int viceVote, int secVote, int treasVote) {
//		RealUser user = realUserRepository.findByUsername(username);
//    	
//    	if (user == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//    	
//    	if(!(user.getHas_voted() == 'Y')) {
//    		user.setChair_vote(chairVote);
//    		user.setVice_vote(viceVote);
//    		user.setSec_vote(secVote);
//    		user.setTreas_vote(treasVote);
//    		realUserRepository.save(user);
//    	}
//    	
//	}
//}
