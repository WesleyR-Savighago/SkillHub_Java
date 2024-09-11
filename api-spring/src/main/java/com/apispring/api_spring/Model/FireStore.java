package com.apispring.api_spring.Model;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.Query;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FireStore {
    
    public static Firestore initializeFirestore() throws IOException {
        if (FirebaseApp.getApps().isEmpty()) {
            FileInputStream serviceAccount = new FileInputStream("C:\\Users\\wesle\\Documents\\Api_Spring_Java\\API_Spring_Java\\api-spring\\src\\main\\java\\com\\apispring\\api_spring\\Settings\\api-springjava-firebase-adminsdk-yfsh7-09ea6dba3c.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://api-springjava-default-rtdb.firebaseio.com/")
                    .build();

            FirebaseApp.initializeApp(options);
        }
        
        return FirestoreClient.getFirestore();

    }
        // FUNÇÃO PARA CRIAR A ORDEM DOS IDS DE REGISTRO.
       public static DocumentSnapshot getLastRecord(String collectionName) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();

        ApiFuture<QuerySnapshot> query = db.collection(collectionName)
                .orderBy("dataRegistro", Query.Direction.DESCENDING)
                .limit(1)
                .get();

        List<QueryDocumentSnapshot> documents = query.get().getDocuments();

        if (!documents.isEmpty()) {
            return documents.get(0);
        } else {
            return null;
        }
    }
}
