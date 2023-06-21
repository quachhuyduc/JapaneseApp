package com.example.japaneseapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;

import com.example.japaneseapp.models.Character;
import com.example.japaneseapp.models.Grammar;
import com.example.japaneseapp.models.Primary;
import com.example.japaneseapp.models.Structure;
import com.example.japaneseapp.models.Vocabulary;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;




public class DatabaseManager {
    public static final String SESSION = "session";
    private static final String DATABASE_PATH = Environment.getDataDirectory().getAbsolutePath() + "/data/com.example.japaneseapp/";
    private static final String DATABSE_NAME = "Japanese.sqlite";
    private static final String TABLE_NAME_KAIWA = "kaiwa";
    private static final String TABLE_NAME_VOCAB = "vocab";
    private static final String TABLE_NAME_KANJI = "kanji";
    private static final String TABLE_NAME_STRUCTURE = "structure";
    private static final String SQL_GET_KAIWA = "SELECT * FROM " + TABLE_NAME_KAIWA;
    private static final String SQL_GET_VOCAB = "SELECT * FROM " + TABLE_NAME_VOCAB;
    private static final String SQL_GET_STRUCTURE = "SELECT * FROM " + TABLE_NAME_STRUCTURE;
    private static final String SQL_GET_KANJI = "SELECT * FROM " + TABLE_NAME_KANJI;

    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String CONTENTS = "contents";
    private static final String TABLE_NAME = "alpha";
    private static final String VOCABULARY = "vocab";
    private static final String PASSWORD_SECRET = "tu4nsh4k3r";
    private SQLiteDatabase sqLiteDatabase;
    private Context context;

    public DatabaseManager(Context context) {

        this.context = context;
        copyDatabases();

    }

    private void copyDatabases() {
        new File(DATABASE_PATH).mkdir();
        try {
            File file = new File(DATABASE_PATH + DATABSE_NAME);
            if (file.exists()) return;
            InputStream input = context.getAssets().open(DATABSE_NAME);

            file.createNewFile();
            FileOutputStream output = new FileOutputStream(file);
            int len;
            byte buff[] = new byte[1024];
            while ((len = input.read(buff)) > 0) {
                output.write(buff, 0, len);
            }
            output.close();
            input.close();

            Log.i("a", "Copy Done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openDB() {
        if (sqLiteDatabase == null || !sqLiteDatabase.isOpen()) {
            sqLiteDatabase = SQLiteDatabase.openDatabase(DATABASE_PATH + DATABSE_NAME, null, SQLiteDatabase.OPEN_READWRITE);
        }
    }

    private void closeDB() {
        if (sqLiteDatabase != null || sqLiteDatabase.isOpen()) {
            sqLiteDatabase.close();
        }
    }


    public ArrayList<Primary> getListPrimary1() {
        openDB();
        //Uu tien lay theo dk sau

        Cursor c = sqLiteDatabase.rawQuery(SQL_GET_KAIWA + " where cast (lesson_id as bigint) between 1 and 25", null);

        if (c == null) {
            return null;
        }
        //Lay thu tu cot dua vao ten cot
        //Lay du lieu dua vao thu tu cot
        int indexLessonId = c.getColumnIndex("lesson_id");
        int indexKaiwaTitle = c.getColumnIndex("kaiwa_title");
        int indexKaiwaContent = c.getColumnIndex("kaiwa_content");


        String lessonId, kaiwaTitle, kaiwaContent;

        c.moveToFirst();
        //Khi con tro chua tro toi hang cuoi cung
        ArrayList<Primary> listPrimary = new ArrayList<>();
        while (c.isAfterLast() == false) {
            //Lay du lieu dua vao thu tu cot

            lessonId = c.getString(indexLessonId);
            kaiwaTitle = c.getString(indexKaiwaTitle);
            kaiwaContent = c.getString(indexKaiwaContent);
           /* listAlpha.add(new Alpha(id, alpha, write, audio));*/
            listPrimary.add(new Primary(lessonId, kaiwaTitle, kaiwaContent));

            //Dua con tro den vi tri tiep theo
            c.moveToNext();
        }
        //Dong con tro lai
        c.close();
        //Dong csdl
        closeDB();

        //Tra ve list Question
        return listPrimary;
    }

    public ArrayList<Primary> getListTalking(String lesson) {
        openDB();
        //Uu tien lay theo dk sau

        Cursor c = sqLiteDatabase.rawQuery(SQL_GET_KAIWA + " where lesson_id=" + "'" + lesson + "'", null);

        if (c == null) {
            return null;
        }
        //Lay thu tu cot dua vao ten cot
        //Lay du lieu dua vao thu tu cot
        int indexLessonId = c.getColumnIndex("lesson_id");
        int indexKaiwaTitle = c.getColumnIndex("kaiwa_title");
        int indexKaiwaContent = c.getColumnIndex("kaiwa_content");


        String lessonId, kaiwaTitle, kaiwaContent;

        c.moveToFirst();
        //Khi con tro chua tro toi hang cuoi cung
        ArrayList<Primary> listPrimary = new ArrayList<>();
        while (c.isAfterLast() == false) {
            //Lay du lieu dua vao thu tu cot

            lessonId = c.getString(indexLessonId);
            kaiwaTitle = c.getString(indexKaiwaTitle);
            kaiwaContent = c.getString(indexKaiwaContent);
           /* listAlpha.add(new Alpha(id, alpha, write, audio));*/
            listPrimary.add(new Primary(lessonId, kaiwaTitle, kaiwaContent));

            //Dua con tro den vi tri tiep theo
            c.moveToNext();
        }
        //Dong con tro lai
        c.close();
        //Dong csdl
        closeDB();

        //Tra ve list Question
        return listPrimary;
    }

    public ArrayList<Structure> getListStructure(String lesson) {
        openDB();
        //Uu tien lay theo dk sau

        Cursor c = sqLiteDatabase.rawQuery(SQL_GET_STRUCTURE + " where lesson_id=" + "'" + lesson + "'", null);

        if (c == null) {
            return null;
        }
        //Lay thu tu cot dua vao ten cot
        //Lay du lieu dua vao thu tu cot
        int indexLessonId = c.getColumnIndex("lesson_id");
        int indexStructureTitle = c.getColumnIndex("name_structure");
        int indexStructureContent = c.getColumnIndex("content_structure");

        String lessonId, structureTitle, structureContent;

        c.moveToFirst();
        //Khi con tro chua tro toi hang cuoi cung
        ArrayList<Structure> listStructure = new ArrayList<>();
        while (c.isAfterLast() == false) {
            //Lay du lieu dua vao thu tu cot

            lessonId = c.getString(indexLessonId);
            structureTitle = decryptPassword(c.getString(indexStructureTitle));
            structureContent = decryptPassword(c.getString(indexStructureContent));
           /* listAlpha.add(new Alpha(id, alpha, write, audio));*/
            listStructure.add(new Structure(lessonId, structureTitle, structureContent));

            //Dua con tro den vi tri tiep theo
            c.moveToNext();
        }
        //Dong con tro lai
        c.close();
        //Dong csdl
        closeDB();

        //Tra ve list Question
        return listStructure;
    }

    public ArrayList<Character> getListCharacter(String lesson) {
        openDB();
        //Uu tien lay theo dk sau

        Cursor c = sqLiteDatabase.rawQuery(SQL_GET_KANJI + " where lesson_id=" + "'" + lesson + "'", null);

        if (c == null) {
            return null;
        }
        //Lay thu tu cot dua vao ten cot
        //Lay du lieu dua vao thu tu cot
        int indexLessonId = c.getColumnIndex("lesson_id");
        int indexCharacterWord = c.getColumnIndex("kanji_tumoi");
        int indexCharacterMean = c.getColumnIndex("kanji_mean");

        String lessonId, characterWord, characterMean;

        c.moveToFirst();

        //Khi con tro chua tro toi hang cuoi cung
        ArrayList<Character> listStructure = new ArrayList<>();
        while (c.isAfterLast() == false) {
            //Lay du lieu dua vao thu tu cot

            lessonId = c.getString(indexLessonId);
            characterWord = decryptPassword(c.getString(indexCharacterWord));
            characterMean = decryptPassword(c.getString(indexCharacterMean));
           /* listAlpha.add(new Alpha(id, alpha, write, audio));*/
            listStructure.add(new Character(lessonId, characterWord,characterMean));

            //Dua con tro den vi tri tiep theo
            c.moveToNext();
        }
        //Dong con tro lai
        c.close();
        //Dong csdl
        closeDB();

        //Tra ve list Question
        return listStructure;
    }

    public String decryptPassword(String encryptedPwd) {
        try {
            DESKeySpec keySpec = new DESKeySpec(
                    PASSWORD_SECRET.getBytes("UTF-8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(keySpec);

            byte[] encryptedWithoutB64 = Base64.decode(encryptedPwd, Base64.DEFAULT);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] plainTextPwdBytes = cipher.doFinal(encryptedWithoutB64);
            return new String(plainTextPwdBytes);
        } catch (Exception e) {
        }
        return encryptedPwd;
    }


    public ArrayList<Primary> getListPrimary2() {
        openDB();
        //Uu tien lay theo dk sau

        Cursor c = sqLiteDatabase.rawQuery(SQL_GET_KAIWA + " where cast (lesson_id as bigint) between 26 and 50", null);

        if (c == null) {
            return null;
        }
        //Lay thu tu cot dua vao ten cot
        //Lay du lieu dua vao thu tu cot
        int indexLessonId = c.getColumnIndex("lesson_id");
        int indexKaiwaTitle = c.getColumnIndex("kaiwa_title");
        int indexKaiwaContent = c.getColumnIndex("kaiwa_content");


        String lessonId, kaiwaTitle, kaiwaContent;

        c.moveToFirst();
        //Khi con tro chua tro toi hang cuoi cung
        ArrayList<Primary> listPrimary2 = new ArrayList<>();
        while (c.isAfterLast() == false) {
            //Lay du lieu dua vao thu tu cot

            lessonId = c.getString(indexLessonId);
            kaiwaTitle = c.getString(indexKaiwaTitle);
            kaiwaContent = c.getString(indexKaiwaContent);
           /* listAlpha.add(new Alpha(id, alpha, write, audio));*/
            listPrimary2.add(new Primary(lessonId, kaiwaTitle, kaiwaContent));

            //Dua con tro den vi tri tiep theo
            c.moveToNext();
        }
        //Dong con tro lai
        c.close();
        //Dong csdl
        closeDB();

        //Tra ve list Question
        return listPrimary2;
    }

    public ArrayList<Vocabulary> getListVocabulary(String level) {
        openDB();
        //Uu tien lay theo dk sau
        Cursor c = sqLiteDatabase.rawQuery(SQL_GET_VOCAB + " where session=" + "'" + level + "'", null);
        if (c == null) {
            return null;
        }
        //Lay thu tu cot dua vao ten cot
        //Lay du lieu dua vao thu tu cot
        int indexVocabularyFileMp3 = c.getColumnIndex("fileMp3");
        int indexSession = c.getColumnIndex("session");
        int indexVocabularyWord = c.getColumnIndex("word");
        int indexVocabularyMeanE = c.getColumnIndex("meanE");
        int indexVocabularyMean = c.getColumnIndex("mean");

        String vocabularyFileMp3;
        String vocabularySession, vocabularyWord, vocabularyMeanE, vocabularyMean;

        c.moveToFirst();
        //Khi con tro chua tro toi hang cuoi cung
        ArrayList<Vocabulary> listVocabulary = new ArrayList<>();
        while (c.isAfterLast() == false) {
            //Lay du lieu dua vao thu tu cot
            vocabularyFileMp3 = c.getString(indexVocabularyFileMp3);
            vocabularySession = c.getString(indexSession);
            vocabularyWord = c.getString(indexVocabularyWord);
            vocabularyMeanE = c.getString(indexVocabularyMeanE);
            vocabularyMean = c.getString(indexVocabularyMean);
           /* listAlpha.add(new Alpha(id, alpha, write, audio));*/
            listVocabulary.add(new Vocabulary(vocabularyFileMp3, vocabularySession, vocabularyWord, vocabularyMeanE, vocabularyMean));

            //Dua con tro den vi tri tiep theo
            c.moveToNext();
        }
        //Dong con tro lai
        c.close();
        //Dong csdl
        closeDB();

        //Tra ve list Question
        return listVocabulary;
    }


    public boolean inSert(String tableName, String[] columns, String[] dataColumns) {
        //Tung tu Bundle luu tru theo kieu key value
        openDB();
        ContentValues values = new ContentValues();
        for (int i = 0; i < columns.length; i++) {
            values.put(columns[i], dataColumns[i]);
        }
        long results = sqLiteDatabase.insert(tableName, null, values);
        //Dong database
        closeDB();
        //Tra ve gia tri true/false sau khi insert data thanhcong/thatbai
        return results > -1;
    }

    public boolean delete(String tableName, String where, String[] whereArgs) {
        //Tung tu Bundle luu tru theo kieu key value
        openDB();
        int results = sqLiteDatabase.delete(tableName, where, whereArgs);
        //Dong database
        closeDB();
        //Tra ve gia tri true/false sau khi insert data thanhcong/thatbai
        return results > 0;
    }


    public List<Grammar> getGrammarDetail(String table) {
        openDB();
        List<Grammar> grammarList = new ArrayList<>();

        String query = "SELECT * FROM " + table;

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Grammar grammar = new Grammar();
                grammar.setId(cursor.getString(cursor.getColumnIndex(ID)));
                grammar.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
                grammar.setContents(cursor.getString(cursor.getColumnIndex(CONTENTS)));

                grammarList.add(grammar);
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();

        for (Grammar grammar : grammarList) {
            grammar.setTitle(grammar.getTitle().replace("=>", ": "));
        }
        return grammarList;
    }

    public List<Vocabulary> getVocabulary(String start, String end) {
        openDB();
        String query = "SELECT *  from " + VOCABULARY + "   where cast (session as bigint) between " +
                start + " and " + end;
        Cursor c = sqLiteDatabase.rawQuery(query, null);

        List<Vocabulary> vocabularies = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                Vocabulary vocabulary = new Vocabulary();
                vocabulary.setWord(c.getString(c.getColumnIndex("word")));
                vocabulary.setFileMp3(c.getString(c.getColumnIndex("fileMp3")));
                vocabulary.setMean(c.getString(c.getColumnIndex("mean")));
                vocabulary.setMeanE(c.getString(c.getColumnIndex("meanE")));
                vocabulary.setSession(c.getString(c.getColumnIndex("session")));
                vocabularies.add(vocabulary);
            } while (c.moveToNext());
        }
        c.close();
        closeDB();
        return vocabularies;
    }
}

