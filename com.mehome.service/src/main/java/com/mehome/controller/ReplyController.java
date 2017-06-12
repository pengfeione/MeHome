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

import com.mehome.requestDTO.ReplyBean;
import com.mehome.service.iface.IReplyService;
import com.mehome.utils.Result;

@RestController
@RequestMapping("/api/reply")
public class ReplyController {
	@Value("${cros}")
	private String cros;
	@Autowired
	private IReplyService replyService;

	@PostMapping("/list")
	@ResponseBody
	public ResponseEntity<Result> list(@RequestBody ReplyBean reply) {
		return ResponseEntity.ok().header("Access-Control-Allow-Origin", cros)
				.contentType(MediaType.APPLICATION_JSON_UTF8).body(Result.build()
						.content(replyService.getListByCondition(reply), replyService.getSizeByCondition(reply)));
	}

	@PostMapping("/add")
	@ResponseBody
	public ResponseEntity<Result> add(@RequestBody ReplyBean reply) {
		return ResponseEntity.ok().header("Access-Control-Allow-Origin", cros)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(Result.build().content(replyService.addReply(reply)));
	}

}
