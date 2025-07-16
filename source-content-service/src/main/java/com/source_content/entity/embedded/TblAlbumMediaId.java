package com.source_content.entity.embedded;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TblAlbumMediaId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "album_id", nullable = false)
    private Long albumId;

    @Column(name = "media_id", nullable = false)
    private Long mediaId;

    public TblAlbumMediaId() {}

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public Long getMediaId() {
        return mediaId;
    }

    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TblAlbumMediaId entity = (TblAlbumMediaId) o;
        return Objects.equals(this.albumId, entity.albumId) &&
                Objects.equals(this.mediaId, entity.mediaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(albumId, mediaId);
    }

}