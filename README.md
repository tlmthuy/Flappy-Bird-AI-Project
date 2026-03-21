# Flappy-Bird-AI-Project
Ứng dụng thuật toán NEAT (Genetic Algorithm kết hợp Neural Network) để huấn luyện một quần thể chim tự học cách vượt qua các chướng ngại vật trong trò chơi Flappy Bird mà không cần sự can thiệp của con người.

📌 **Thành phần dự án**

• Flappy X (Android): Ứng dụng di động được xây dựng trên Android Studio.

• AI Trainer (Python): Sử dụng thuật toán NEAT (NeuroEvolution of Augmenting Topologies) để huấn luyện chim vượt qua chướng ngại vật.

📌 **Cơ chế Học máy** 
• Mô hình mạng thần kinh (Neural Network) nhận diện môi trường thông qua 3 tham số đầu vào (Inputs):

 + Bird Y: Vị trí hiện tại của chim trên trục tung.

 + Top Pipe Dist: Khoảng cách tới đỉnh ống cống gần nhất.

 + Bottom Pipe Dist: Khoảng cách tới đáy ống cống gần nhất.

• Hàm Fitness (Đánh giá):

 + Cộng điểm khi chim sống.

 + (+5) khi vượt qua ống thành công.

 + (-1) và loại bỏ khi va chạm ống hoặc mặt đất.

📌 **Công nghệ sử dụng**
• Languages: Python, Java/Kotlin (Android).

• Libraries: Pygame, NEAT-python.

• Tools: Android Studio, Git, GitHub Desktop.

📌 **Kết quả huấn luyện**
Sau khoảng 2-5 thế hệ (Generations), mô hình tìm ra "cá thể ưu tú" có khả năng chơi vô hạn mà không va chạm. 
