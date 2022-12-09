package talent.squad.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import talent.squad.QRgenerator.QR;

@RestController
public class Controller {
	
	@GetMapping("/getQr")
	public QR getQr(@RequestBody String text) {
		
		return null;
	}

}
