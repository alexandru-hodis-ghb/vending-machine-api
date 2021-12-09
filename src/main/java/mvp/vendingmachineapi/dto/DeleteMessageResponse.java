package mvp.vendingmachineapi.dto;

import org.springframework.http.HttpStatus;

public class DeleteMessageResponse {

    private String status = "200";
    private String message = "Successfully deleted.";

    public DeleteMessageResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
