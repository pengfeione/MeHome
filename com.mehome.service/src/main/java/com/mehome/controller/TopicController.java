package com.mehome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mehome.requestDTO.TopicBean;
import com.mehome.service.iface.ITopicService;
import com.mehome.utils.Result;

@RestController
@RequestMapping("/api/topic")
public class TopicController {
	@Value("${cros}")
	private String cros;
	@Autowired
	private ITopicService topicService;

	@PostMapping("/list")
	@ResponseBody
	public ResponseEntity<Result> list(@RequestBody TopicBean topic) {
		return ResponseEntity.ok().header("Access-Control-Allow-Origin", cros)
				.contentType(MediaType.APPLICATION_JSON_UTF8).body(Result.build()
						.content(topicService.getListByCondition(topic), topicService.getSizeByCondition(topic)));
	}
	@PostMapping("/add")
	@ResponseBody
	public ResponseEntity<Result> add(@RequestBody TopicBean topic) {
		return ResponseEntity.ok().header("Access-Control-Allow-Origin", cros)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(Result.build().content(topicService.addTopic(topic)));
	}

	@PostMapping("/update")
	@ResponseBody
	public ResponseEntity<Result> update(@RequestBody TopicBean topic) {
		return ResponseEntity.ok().header("Access-Control-Allow-Origin", cros)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(Result.build().content(topicService.updateTopic(topic)));
	}
}
