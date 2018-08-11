package com.refs.services;

import com.refs.commands.CommentCommand;
import com.refs.models.Comment;

import java.util.Set;

public interface CommentService {

    Set<Comment> getComments();


    CommentCommand saveCommentCommand(CommentCommand commentCommand);
}
