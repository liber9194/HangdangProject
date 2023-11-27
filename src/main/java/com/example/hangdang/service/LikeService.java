package com.example.hangdang.service;

import com.example.hangdang.entity.Goods;
import com.example.hangdang.entity.Like;
import com.example.hangdang.entity.UserEntity;
import com.example.hangdang.repository.GoodsRepository;
import com.example.hangdang.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final GoodsRepository goodsRepository;
    private final LikeRepository likeRepository;

    // 좋아요 등록
    @Transactional
    public void like(Long goodsId, UserEntity user) {
        Goods goods = goodsRepository.findById(goodsId)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다. id=" + goodsId));
        Like like = new Like(goods, user);
        likeRepository.save(like);
    }

    // 좋아요 취소
    @Transactional
    public void unlike(Long goodsId, Long likeId, UserEntity user) {
        Like like = likeRepository.findById(likeId)
                .orElseThrow(() -> new IllegalArgumentException("해당 좋아요가 존재하지 않습니다."));
        if (like.getGoods().getId().equals(goodsId) && like.getUser().getId().equals(user.getId())) {
            likeRepository.delete(like);
        } else {
            throw new IllegalArgumentException("삭제하려는 좋아요 정보가 일치하지 않습니다.");
        }
    }
}
