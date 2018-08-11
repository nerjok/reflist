package com.refs.converters;

import com.refs.commands.CommentCommand;
import com.refs.models.Advertisement;
import com.refs.models.Comment;
import com.refs.services.AdvertisementService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommentCommandToComment implements Converter<CommentCommand, Comment> {
    final AdvertisementService advertisementService;

    public CommentCommandToComment(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @Override
    public Comment convert(CommentCommand source) {
        if (source == null) {
            return null;
        }

        final Comment comment = new Comment();

        comment.setId(source.getId());
        comment.setComment(source.getComment());
        Advertisement advertisement = advertisementService.findById(source.getAdvertisementId());
        comment.setAdvertisement(advertisement);
        return comment;

    }
}
