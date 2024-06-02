# Define the project directories and files
DOCKER_COMPOSE_FILE = docker-compose.yml
SPRING_BOOT_APP_DIR = .
SPRING_BOOT_APP_IMAGE = micrometer-app

# Targets
.PHONY: up build start down clean logs test

# Build the Spring Boot application and Docker images, then start all services
up: build start

# Build the Spring Boot application and Docker images
build:
	@echo "Building Spring Boot application with Gradle..."
	cd $(SPRING_BOOT_APP_DIR) && ./gradlew build
	@echo "Building Docker images..."
	docker-compose -f $(DOCKER_COMPOSE_FILE) build --no-cache

# Start all services using Docker Compose
start:
	@echo "Starting all services with Docker Compose..."
	docker-compose -f $(DOCKER_COMPOSE_FILE) up -d --force-recreate

# Stop and remove all Docker containers and networks
down:
	@echo "Stopping and removing all services..."
	docker-compose -f $(DOCKER_COMPOSE_FILE) down

# Remove all Docker containers, networks, volumes, and images created by up
clean:
	@echo "Removing all Docker containers, networks, volumes, and images..."
	docker-compose -f $(DOCKER_COMPOSE_FILE) down -v --rmi all --remove-orphans

# Show logs for all services
logs:
	@echo "Showing logs for all services..."
	docker-compose -f $(DOCKER_COMPOSE_FILE) logs -f

# Run tests (optional)
test:
	@echo "Running tests for Spring Boot application..."
	cd $(SPRING_BOOT_APP_DIR) && ./gradlew test
