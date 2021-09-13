package com.example.bigfiveprofile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

// For Response status
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import java.net.URI;

@Controller
@RequestMapping(path="/api")
public class MainController {
	@Autowired
	private BigFiveProfileRepository profileRepository;

	@PostMapping(path="/add")
	public @ResponseBody String addNewProfile (@RequestParam String name
                                             , @RequestParam String email) {
		BigFiveProfile n = new BigFiveProfile();
		n.setName(name);
		n.setEmail(email);
		profileRepository.save(n);
		return "Saved";
	}

    @PostMapping(path="/big_five_profile_submissions")
    @ResponseBody
    public ResponseEntity<String> addNewProfile (@RequestBody BigFiveProfile profile) {
        BigFiveProfile n = profile;
        profileRepository.save(n);
        System.out.println("Profile SAVE:\n======\n" + profile.toString());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "MainController.addNewProfile()");
        return ResponseEntity.created(URI.create("big_five_profile_submissions/" + profile.getId())).headers(headers).body("Profile posted and saved");
    }


	@GetMapping(path="/all")
	public @ResponseBody Iterable<BigFiveProfile> getAllProfiless() {
		// This returns a JSON or XML with the users
		return profileRepository.findAll();
	}
}
 // This means URL's start with /demo (after Application path)
