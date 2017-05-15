package com.mehome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mehome.requestDTO.CommentBean;
import com.mehome.requestDTO.HouseBean;
import com.mehome.service.iface.ICommentService;
import com.mehome.service.iface.IHouseService;
import com.mehome.service.iface.ISupplierService;
import com.mehome.utils.Result;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
	private final static String cros="*";
	@Autowired
	private ICommentService commentService;
	@PostMapping("/list")
	@ResponseBody
	public ResponseEntity<Result> list(@RequestBody CommentBean bean){
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build("点评列表请求/comment/list").content(commentService.getListByCondition(bean),commentService.getSizeByCondition(bean)));
    }
	@PostMapping("/add")
	@ResponseBody
	public ResponseEntity<Result> add(@RequestBody CommentBean bean){
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build("点评添加请求/comment/add").content(commentService.addComment(bean)));
    }
	@PostMapping("/update")
	@ResponseBody
	public ResponseEntity<Result> update(@RequestBody CommentBean bean){
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build("点评修改请求/comment/update").content(commentService.updateComment(bean)));
    }

}
