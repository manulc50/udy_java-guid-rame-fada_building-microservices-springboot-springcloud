package net.mlorenzo.kafkaconsumerwikimedia.repository;

import net.mlorenzo.kafkaconsumerwikimedia.entity.WikimediaData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WikimediaDataRespository extends JpaRepository<WikimediaData, Long> {
}
