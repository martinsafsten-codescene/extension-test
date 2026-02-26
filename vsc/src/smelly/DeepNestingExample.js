/**
 * Deep Nesting - Too many nested control structures.
 * CodeScene flags methods with excessive indentation levels.
 */

export class DeepNestingExample {
  validateAndProcessUser(
    username,
    password,
    email,
    age,
    country,
    hasAgreedToTerms
  ) {
    if (username != null) {
      if (username.length >= 3) {
        if (username.length <= 20) {
          if (password != null) {
            if (password.length >= 8) {
              if (/[A-Z]/.test(password)) {
                if (/\d/.test(password)) {
                  if (email != null) {
                    if (email.includes("@")) {
                      if (email.includes(".")) {
                        if (age >= 13) {
                          if (age <= 120) {
                            if (country != null) {
                              if (["US", "UK", "CA"].includes(country)) {
                                if (hasAgreedToTerms) {
                                  console.log("User validation successful");
                                  this.createUserAccount(
                                    username,
                                    password,
                                    email
                                  );
                                } else {
                                  console.error("User must agree to terms");
                                }
                              } else {
                                console.error(
                                  "Service not available in country"
                                );
                              }
                            } else {
                              console.error("Country is required");
                            }
                          } else {
                            console.error("Age must be realistic");
                          }
                        } else {
                          console.error("User must be at least 13 years old");
                        }
                      } else {
                        console.error("Email must contain domain extension");
                      }
                    } else {
                      console.error("Email must contain @ symbol");
                    }
                  } else {
                    console.error("Email is required");
                  }
                } else {
                  console.error("Password must contain at least one number");
                }
              } else {
                console.error(
                  "Password must contain at least one uppercase letter"
                );
              }
            } else {
              console.error("Password must be at least 8 characters");
            }
          } else {
            console.error("Password is required");
          }
        } else {
          console.error("Username too long");
        }
      } else {
        console.error("Username too short");
      }
    } else {
      console.error("Username is required");
    }
  }

  createUserAccount(username, password, email) {
    console.log(`Creating account for: ${username}`);
  }
}
