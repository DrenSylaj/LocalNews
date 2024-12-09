package com.localnews.lajmi.repository;

import com.localnews.lajmi.entity.Lajmi;
import com.localnews.lajmi.entity.Tag;
import com.localnews.lajmi.entity.TagLajmi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TagLajmiRepository extends JpaRepository<TagLajmi, Integer> {

    Optional<TagLajmi> findByLajmiIdAndTagId(Integer lajmiId, Integer tagId);

    @Query("SELECT tl.tag From TagLajmi tl WHERE tl.lajmi.id = :lajmiId")
    List<Tag> findTagsByLajmiId(@Param("lajmiId") Integer lajmiId);

    List<TagLajmi> findByLajmiId(Integer id);

    @Query("SELECT tl.lajmi From TagLajmi tl WHERE tl.tag.id = :tagId")
    List<Lajmi> findLajmeByTagId(@Param("tagId") Integer tagId);
}
