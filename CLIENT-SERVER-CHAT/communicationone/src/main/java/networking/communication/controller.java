package networking.communication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/data")

public class controller {

  @Autowired
  private DataRepository datarepo;

    @PostMapping("/print")
    public ResponseEntity<String> receivePythonData(@RequestBody DataFromPython data) {
        datarepo.insertdataifnot(data.getId(),data.getClient());
       return ResponseEntity.ok("JSON printed to console!");
        
    }
    
}
