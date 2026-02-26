package org.example.smelly

/**
 * Deep Nesting - Too many nested control structures.
 * CodeScene flags methods with excessive indentation levels.
 */
class DeepNestingExample {

    fun validateAndProcessUser(
        username: String?,
        password: String?,
        email: String?,
        age: Int,
        country: String?,
        hasAgreedToTerms: Boolean
    ) {
        if (username != null) {
            if (username.length >= 3) {
                if (username.length <= 20) {
                    if (password != null) {
                        if (password.length >= 8) {
                            if (password.any { it.isUpperCase() }) {
                                if (password.any { it.isDigit() }) {
                                    if (email != null) {
                                        if (email.contains("@")) {
                                            if (email.contains(".")) {
                                                if (age >= 13) {
                                                    if (age <= 120) {
                                                        if (country != null) {
                                                            if (country in listOf("US", "UK", "CA")) {
                                                                if (hasAgreedToTerms) {
                                                                    println("User validation successful")
                                                                    createUserAccount(username, password, email)
                                                                } else {
                                                                    System.err.println("User must agree to terms")
                                                                }
                                                            } else {
                                                                System.err.println("Service not available in country")
                                                            }
                                                        } else {
                                                            System.err.println("Country is required")
                                                        }
                                                    } else {
                                                        System.err.println("Age must be realistic")
                                                    }
                                                } else {
                                                    System.err.println("User must be at least 13 years old")
                                                }
                                            } else {
                                                System.err.println("Email must contain domain extension")
                                            }
                                        } else {
                                            System.err.println("Email must contain @ symbol")
                                        }
                                    } else {
                                        System.err.println("Email is required")
                                    }
                                } else {
                                    System.err.println("Password must contain at least one number")
                                }
                            } else {
                                System.err.println("Password must contain at least one uppercase letter")
                            }
                        } else {
                            System.err.println("Password must be at least 8 characters")
                        }
                    } else {
                        System.err.println("Password is required")
                    }
                } else {
                    System.err.println("Username too long")
                }
            } else {
                System.err.println("Username too short")
            }
        } else {
            System.err.println("Username is required")
        }
    }

    private fun createUserAccount(username: String, password: String, email: String) {
        println("Creating account for: $username")
    }
}
