package ru.appavlov.bookshelf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.appavlov.bookshelf.model.Vote;
import ru.appavlov.bookshelf.repository.VoteRepository;
import ru.appavlov.bookshelf.service.dao.VoteDao;

import java.util.List;

// API сервисного уровня для работы с голосам
@Service
@Transactional
public class VoteService implements VoteDao {
    @Autowired
    private VoteRepository voteRepository;


    @Override
    public List<Vote> getAll() {
        return voteRepository.findAll();
    }

    @Override
    public List<Vote> search(String... searchString) {
        return voteRepository.findByUsernameContainingIgnoreCaseOrderByUsername(searchString[0]);
    }

    @Override
    public Vote get(long id) {
        return voteRepository.findOne(id);
    }

    @Override
    public Vote save(Vote obj) {
        return voteRepository.save(obj);
    }

    @Override
    public void delete(Vote object) {
        voteRepository.delete(object);
    }

    @Override
    public List<Vote> getAll(Sort sort) {
        return voteRepository.findAll(sort);
    }

    @Override
    public Page<Vote> getAll(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection) {
        return voteRepository.findAll(new PageRequest(pageNumber, pageSize, new Sort(sortDirection, sortField)));
    }

    @Override
    public Page<Vote> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, String... searchString) {
        return voteRepository.findByUsernameContainingIgnoreCaseOrderByUsername(searchString[0], new PageRequest(pageNumber, pageSize, new Sort(sortDirection, sortField)));
    }
}
