package org.seasar.doma.boot;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@AllArgsConstructor
public class DomaBootSampleSimpleApplication {

	MessageDao messageDao;

	@RequestMapping("/")
	List<Message> list(@PageableDefault Pageable pageable) {
		return messageDao.selectAll(Pageables.toSelectOptions(pageable));
	}

	@RequestMapping(value = "/", params = "text")
	Message add(@RequestParam String text) {
		Message message = new Message();
		message.text = text;
		messageDao.insert(message);
		return message;
	}

	public static void main(String[] args) {
		SpringApplication.run(DomaBootSampleSimpleApplication.class, args);
	}
}
