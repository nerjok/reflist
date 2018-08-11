package com.refs.services;

import com.refs.commands.CommentCommand;
import com.refs.converters.CommentCommandToComment;
import com.refs.converters.CommentToCommentCommand;
import com.refs.models.Comment;
import com.refs.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CommentServiceImpl  implements CommentService {

    private final CommentCommandToComment commentCommandToComment;
    private final CommentToCommentCommand commentToCommentCommand;
    private final CommentRepository commentRepository;


    public CommentServiceImpl(CommentCommandToComment commentCommandToComment, CommentToCommentCommand commentToCommentCommand, CommentRepository commentRepository) {
        this.commentCommandToComment = commentCommandToComment;
        this.commentToCommentCommand = commentToCommentCommand;
        this.commentRepository = commentRepository;
    }

    @Override
    public Set<Comment> getComments() {
        return null;
    }

    @Override
    public CommentCommand saveCommentCommand(CommentCommand commentCommand) {
        Comment detachedComment = commentCommandToComment.convert(commentCommand);

        Comment savedCategory = commentRepository.save(detachedComment);

        return commentToCommentCommand.convert(savedCategory);
    }
}
