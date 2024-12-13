package com.example.SpringRpg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.SpringRpg.Entities.Actor;
import com.example.SpringRpg.Entities.Location;
import com.example.SpringRpg.Entities.LocationLink;
import com.example.SpringRpg.Entities.User;
import com.example.SpringRpg.Repositories.ActorRepository;
import com.example.SpringRpg.Repositories.LocationLinkRepository;
import com.example.SpringRpg.Repositories.LocationRepository;
import com.example.SpringRpg.Repositories.UserRepository;

@Controller
public class MainController {
	@Autowired // This means to get the bean called userRepository
			   // Which is auto-generated by Spring, we will use it to handle the data
	private UserRepository userRepository;
	@Autowired
	private LocationRepository locationRepository;
	@Autowired
	private ActorRepository actorRepository;
	@Autowired
	private LocationLinkRepository locationLinkRepository;

	@GetMapping(path="/add") // Map ONLY POST Requests
	public @ResponseBody String addNewUser (@RequestParam String name
			, @RequestParam String email) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		User n = new User();
		n.setName(name);
		n.setEmail(email);        
		userRepository.save(n);
		return "Saved";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}

	@GetMapping("/game")
	public String game(@RequestParam(name="userName", required = true, defaultValue = "") String userName, Model model) {
		User user = userRepository.findByName(userName);
		Actor actor = user.getActor();
		Location location = actor.getLocation();
		List<LocationLink> links = locationLinkRepository.findAllBySourceLocation(location);
		model.addAttribute("userName", userName);
		model.addAttribute("actorName", actor.getName());
		model.addAttribute("locationName", location.getName());
		model.addAttribute("locationDescription", location.getDescription());
		model.addAttribute("adjacentLocations", links);
		return "game";
	}
	
	// Move actor to different location
	@RequestMapping(value="/move", method = RequestMethod.POST)
	public String move (@RequestParam String userName, @RequestParam String actorName, @RequestParam String destinationLocation) {
		var loc = locationRepository.findByName(destinationLocation);
		var actor = actorRepository.findByName(actorName);
		actor.setLocation(loc);
		actorRepository.save(actor);
		return "redirect:/game?userName=" + userName;
	}
}
