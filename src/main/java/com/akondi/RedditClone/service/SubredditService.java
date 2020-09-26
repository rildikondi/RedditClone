package com.akondi.RedditClone.service;

import com.akondi.RedditClone.dto.SubredditDto;
import com.akondi.RedditClone.exceptions.SpringRedditException;
import com.akondi.RedditClone.mapper.SubredditMapper;
import com.akondi.RedditClone.model.Subreddit;
import com.akondi.RedditClone.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class SubredditService {

    private final SubredditRepository subredditRepository;
    private final SubredditMapper subredditMapper;

    @Transactional
    public SubredditDto save(SubredditDto subredditDto) {
        Subreddit subredditMaped = subredditMapper.mapDtoToSubreddit(subredditDto);
        Subreddit subredditSaved =   subredditRepository.save(subredditMaped);
        subredditDto.setId(subredditSaved.getId());
        return subredditDto;
    }

    @Transactional(readOnly =  true)
    public List<SubredditDto> getAll() {
        return subredditRepository.findAll()
                .stream()
                .map(subredditMapper::mapSubredditToDto)
                .collect(Collectors.toList());
    }

    public SubredditDto getSubreddit(Long id) {
        Subreddit subreddit = subredditRepository.findById(id)
                .orElseThrow(() -> new SpringRedditException("No subreddit found with id: " + id));
        return subredditMapper.mapSubredditToDto(subreddit);
    }
}
