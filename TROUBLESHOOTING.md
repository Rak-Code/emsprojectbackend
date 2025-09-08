# Troubleshooting Guide

## Common Issues and Solutions

### 1. Swagger UI 500 Error - `/v3/api-docs` endpoint

**Problem**: Getting HTTP 500 error when accessing Swagger documentation

**Causes & Solutions**:

#### ✅ **FIXED**: Version Compatibility Issues
- **Issue**: Spring Boot 3.5.5 incompatible with SpringDoc OpenAPI and Java 24
- **Solution**: Downgraded to stable versions:
  - Spring Boot: `3.5.5` → `3.2.1`
  - Java: `24` → `17`
  - SpringDoc: `2.2.0` → `2.3.0`
- **Root Cause**: `NoSuchMethodError: ControllerAdviceBean.<init>` due to version mismatch

#### ✅ **FIXED**: SpringDoc Dependencies
- **Issue**: Incompatible SpringDoc versions or missing dependencies
- **Solution**: Updated SpringDoc to compatible version `2.3.0`
- **Removed**: Incompatible `springdoc-openapi-security:1.8.0`

#### ✅ **FIXED**: JWT Filter Configuration  
- **Issue**: JWT filter was processing OpenAPI endpoints causing authentication errors
- **Solution**: Updated `JwtAuthFilter.shouldNotFilter()` to exclude:
  - `/swagger-ui/**`
  - `/v3/api-docs/**` 
  - `/swagger-resources/**`
  - `/webjars/**`

#### ✅ **FIXED**: Security Configuration
- **Issue**: Security rules blocking OpenAPI endpoints
- **Solution**: Enhanced SecurityConfig to permit all OpenAPI paths

#### ✅ **FIXED**: OpenAPI Configuration
- **Issue**: Missing OpenAPI configuration bean
- **Solution**: Added `OpenApiConfig.java` with proper JWT authentication setup

### 2. Email Service Compilation Error

**Problem**: `JavaMailSender` and `SimpleMailMessage` cannot be resolved

#### ✅ **FIXED**: Missing Mail Dependency
- **Solution**: Added `spring-boot-starter-mail` dependency
- **Enhanced**: Made email service optional with `@ConditionalOnProperty`
- **Improved**: Added error handling and template methods

### 3. Authentication Issues

**Problem**: JWT authentication not working properly

#### ✅ **FIXED**: Password Encoder Bean
- **Issue**: Multiple BCryptPasswordEncoder instances
- **Solution**: Created single `@Bean` in SecurityConfig

#### ✅ **FIXED**: User Service Injection
- **Issue**: UserService creating its own password encoder
- **Solution**: Injected BCryptPasswordEncoder via constructor

### 4. Database Connection Issues

**Problem**: Cannot connect to MySQL database

**Solutions**:
1. **Check MySQL Status**: Ensure MySQL service is running
2. **Verify Database**: Create database `employee_management_system`
3. **Update Credentials**: Configure proper username/password in `application.properties`
4. **Check URL**: Verify `spring.datasource.url` is correct

### 5. Build Issues

**Problem**: Maven compilation errors

**Solutions**:
1. **Clean Build**: Run `mvn clean compile`
2. **Check Java Version**: Ensure Java 17 is installed and JAVA_HOME is set
3. **Update Dependencies**: Run `mvn dependency:resolve`
4. **Clear Cache**: Delete `.m2/repository` folder if needed

## API Testing

### Swagger UI Access
- **URL**: `http://localhost:8080/swagger-ui.html`
- **API Docs**: `http://localhost:8080/v3/api-docs`

### Authentication Flow
1. Register admin user via `/api/auth/register`
2. Login to get JWT token via `/api/auth/login`
3. Use token in Authorization header: `Bearer <your-token>`

### Quick Test Commands
```bash
# Register Admin
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","email":"admin@test.com","password":"admin123","role":"ADMIN"}'

# Login
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"usernameOrEmail":"admin","password":"admin123"}'
```

## Configuration Tips

### 1. Email Configuration (Optional)
If you want email functionality, uncomment in `application.properties`:
```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_email@gmail.com
spring.mail.password=your_email_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

### 2. Development vs Production
- **Development**: Use H2 database for quick testing
- **Production**: Use MySQL with proper connection pooling

### 3. Security Configuration
- **Development**: CORS is configured for `localhost:5173`
- **Production**: Update CORS origins for your domain

## Performance Tips

1. **Database Indexing**: Add indexes on frequently queried fields
2. **Connection Pooling**: Configure HikariCP settings
3. **Caching**: Add Redis for session management
4. **Logging**: Configure proper log levels for production

## Monitoring

### Health Check Endpoints
- **Application**: `http://localhost:8080/actuator/health` (if enabled)
- **Database**: Check connection via any API endpoint

### Log Monitoring
- Check application logs for errors
- Monitor MySQL slow query logs
- Track JWT token validation failures

## Support

If issues persist:
1. Check application logs for detailed error messages
2. Verify all dependencies are properly resolved
3. Ensure database connectivity
4. Test with simple curl commands before complex integration