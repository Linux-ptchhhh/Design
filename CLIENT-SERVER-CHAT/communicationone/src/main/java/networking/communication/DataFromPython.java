package networking.communication;
import jakarta.persistence.*;

@Entity
@Table(name = "ipinfo")
public class DataFromPython {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String client;

    // Default Constructor
    public DataFromPython() {}

    // CORRECTED: Capital 'C', correct data type (String), matching parameter
    public String getClient() { 
        return client; 
    }
    
    public void setClient(String client) { 
        this.client = client; 
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    @Override
    public String toString() {
        return "DataFromPython {client='" + client + "' }";
    }
}