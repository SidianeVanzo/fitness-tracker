# **Fitness Tracker API 🏋️‍♂️🥗**
A RESTful API built with Scala using Akka HTTP. This API is in development but will enable users to track workouts, nutrition, and progress over time.

---

## **1️⃣ Tech Stack**
- **Framework:** Akka HTTP
- **Database:** PostgreSQL (Slick)
- **Logging & Monitoring:** Elasticsearch + Logstash
- **Testing:** ScalaTest (TO-DO)

---

## **2️⃣ Current Features**

### **🥗 Nutrition Tracking**
- Log meals and calorie intake
- Retrieve daily/weekly calorie consumption

### **📡 Logging & Monitoring**
- Store application logs in Elasticsearch via Logstash
- Monitor API requests, errors, and system health

---

## **3️⃣ API Endpoints**
- `POST /api/nutrition` - Log food intake
- `GET /api/nutrition` - Get daily food log

---

## **4️⃣ Setup & Deployment**
### **🔧 Run Locally with sbt**
1. Clone the repository:
   ```sh
   git clone https://github.com/your-repo/fitness-tracker.git
   cd fitness-tracker
