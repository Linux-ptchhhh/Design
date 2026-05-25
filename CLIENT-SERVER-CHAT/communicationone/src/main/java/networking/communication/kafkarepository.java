package networking.communication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;


@Repository
public interface kafkarepository extends JpaRepository<KafkaEntity, String> {
    @Modifying
    @Transactional
    
    @Query(value = "DELETE FROM kafka WHERE ip = :ip", nativeQuery = true)
    void delete(@Param("ip") String ip);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO kafka (ip,status) VALUES (:ip,:status) ON CONFLICT (ip) DO NOTHING",nativeQuery = true)
    void add(@Param("ip") String ip,@Param("status") String status);
  
}

