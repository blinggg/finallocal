package com.example.mapper;

import java.util.List;

import com.example.domain.Criteria;
import com.example.domain.Lend_CostumeVO;

public interface Lend_CostumeMapper {
    public List<Lend_CostumeVO> list(Criteria cri);
    public void insert(Lend_CostumeVO vo);
    public Lend_CostumeVO read(String lend_costume_code);
    public int totalCount(Criteria cri);
    public void update(Lend_CostumeVO vo);
    public void delete(String lend_costume_code);
}