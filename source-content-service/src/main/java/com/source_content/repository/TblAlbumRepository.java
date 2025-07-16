package com.source_content.repository;

import com.source_content.entity.TblAlbum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TblAlbumRepository extends JpaRepository<TblAlbum, Long> {
}