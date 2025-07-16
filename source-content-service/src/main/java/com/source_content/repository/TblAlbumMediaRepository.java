package com.source_content.repository;

import com.source_content.entity.TblAlbumMedia;
import com.source_content.entity.embedded.TblAlbumMediaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TblAlbumMediaRepository extends JpaRepository<TblAlbumMedia, TblAlbumMediaId> {
}