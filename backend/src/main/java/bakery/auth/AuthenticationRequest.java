package bakery.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthenticationRequest {

	@NotEmpty(message = "Email is mandatory")
	@NotBlank(message = "Email is mandatory")
	@Email(message = "Email is not formatted")
	private String userEmail;
	
	@NotEmpty(message = "Password is mandatory")
	@NotBlank(message = "Password is mandatory")
	@Size(min = 8, message = "Password should be 8 caracters long minimum")
	private String password;
}
