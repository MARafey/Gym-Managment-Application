import cv2
import mediapipe as mp

def count_bicep_curls():
    # Initialize MediaPipe Hands
    mp_hands = mp.solutions.hands
    hands = mp_hands.Hands()
    mp_drawing = mp.solutions.drawing_utils

    # Variables for counting bicep curls
    counter = 0
    curling = False

    # Thresholds for hand movement
    shoulder_threshold = 0.3  # Adjust this value based on your shoulder's position on the screen
    elbow_threshold = 0.6  # Adjust this value based on your elbow's position on the screen

    # Function to detect bicep curls
    def detect_bicep_curls(landmarks, counter, curling):
        index_finger_tip_y = landmarks[mp_hands.HandLandmark.INDEX_FINGER_TIP].y
        index_finger_dip_y = landmarks[mp_hands.HandLandmark.INDEX_FINGER_DIP].y

        if not curling and index_finger_tip_y < shoulder_threshold:
            curling = True
        elif curling and index_finger_tip_y > elbow_threshold:
            curling = False
            counter += 1
            print("Bicep Curl Detected! Count:", counter)

        return counter, curling

    # Open webcam
    cap = cv2.VideoCapture(0)

    while cap.isOpened():
        ret, frame = cap.read()
        if not ret:
            break

        # Convert the image to RGB
        image_rgb = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)

        # Process the image with MediaPipe
        results = hands.process(image_rgb)

        if results.multi_hand_landmarks:
            for hand_landmarks in results.multi_hand_landmarks:
                # Draw landmarks on the image
                mp_drawing.draw_landmarks(frame, hand_landmarks, mp_hands.HAND_CONNECTIONS)

                # Get landmarks
                landmarks = {landmark: hand_landmarks.landmark[landmark] for landmark in
                             range(len(hand_landmarks.landmark))}

                # Detect bicep curls
                counter, curling = detect_bicep_curls(landmarks, counter, curling)

        cv2.imshow('Bicep Curl Counter', frame)

        if cv2.waitKey(1) & 0xFF == ord('q'):
            break

    # Release the webcam and close windows
    cap.release()
    cv2.destroyAllWindows()

# Call the function to start counting bicep curls
count_bicep_curls()
