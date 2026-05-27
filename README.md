# Yachts Shop - Cloud Native Microservices

This project is a demonstration of refactoring a monolithic application into a Cloud Native microservices architecture using Java Spring Boot, Docker, and Azure Container Apps.

## 🏗️ Decomposition Strategy

The initial monolithic e-commerce application was decomposed into two independent bounded contexts using Domain-Driven Design (DDD) principles:

1. **Yacht Catalog Service (Product Bounded Context)**
    - **Responsibility:** Manages the inventory, details, and pricing of the yachts.
    - **Data Management:** Owns its independent database.
    - **Port:** `8081`

2. **Order Service (Sales/Transaction Bounded Context)**
    - **Responsibility:** Handles customer order placement.
    - **Data Management:** Owns its independent database (Database-per-service pattern). It does not share tables with the catalog.
    - **Communication:** Uses **Spring Cloud OpenFeign** to make synchronous HTTP calls to the Catalog Service to verify yacht existence and fetch the current price before saving the order.
    - **Port:** `8082`

## ⚖️ Architecture Trade-offs

By moving from a monolith to this microservices architecture, several trade-offs were made:

### Pros (Advantages)
* **Independent Deployments:** The Catalog and Order services are containerized separately. A new feature in the Catalog service can be deployed without taking the Order service offline.
* **Independent Scalability:** If the website receives high traffic from users simply browsing yachts, the Catalog Service can be scaled horizontally independently of the Order Service.
* **Fault Isolation:** If the Order Service's database crashes, the Catalog Service remains unaffected, allowing users to continue browsing the inventory.

### Cons (Challenges)
* **Operational Complexity:** Instead of deploying one `.jar` file, the system now requires container orchestration (Docker Compose locally, Azure Container Apps in the cloud) and CI/CD pipelines to manage multiple deployments.
* **Network Latency:** In a monolith, fetching a yacht's price was an in-memory method call (~nanoseconds). Now, it is an HTTP network call, which introduces latency and potential network failure points.
* **Data Consistency:** Because the databases are split, there are no SQL `JOIN`s or foreign keys enforcing integrity between an Order and a Yacht.

## ☁️ DevOps & Deployment

The CI/CD workflow is fully automated via **Azure DevOps Pipelines**:
1. **CI (Continuous Integration):** Maven compiles the code and runs tests.
2. **Containerization:** Multi-stage `Dockerfile`s build lightweight JRE images and push them to an **Azure Container Registry (ACR)**.
3. **CD (Continuous Deployment):** Azure CLI commands automatically update the revisions in **Azure Container Apps**, allowing the services to discover each other via internal DNS (Service Discovery).