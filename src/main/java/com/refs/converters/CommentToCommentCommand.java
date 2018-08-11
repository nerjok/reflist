package com.refs.converters;

import com.refs.commands.CategoryCommand;
import com.refs.commands.CommentCommand;
import com.refs.models.Comment;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommentToCommentCommand implements Converter<Comment, CommentCommand> {

    @Override
    public CommentCommand convert(Comment source) {
        if (source == null) {
            return null;
        }

        final CommentCommand commentCommand = new CommentCommand();

        commentCommand.setId(source.getId());
        commentCommand.setComment(source.getComment());
        commentCommand.setAdvertisementId(source.getAdvertisement().getId());

        return commentCommand;
    }
}
