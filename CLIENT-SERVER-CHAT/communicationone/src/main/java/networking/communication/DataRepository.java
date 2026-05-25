package networking.communication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface DataRepository extends JpaRepository<DataFromPython, Long> {
    @Modifying
    @Transactional
    
    @Query(value = "INSERT INTO ipconfig (id,client) VALUES (:id, :client) ON CONFLICT (client) DO NOTHING", nativeQuery = true)
    void insertdataifnot(@Param("id") Long id,@Param("client") String client);
}
