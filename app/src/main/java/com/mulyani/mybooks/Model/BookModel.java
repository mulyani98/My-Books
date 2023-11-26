package com.mulyani.mybooks.Model;

import android.os.Parcel;
import android.os.Parcelable;

/* data that will be stored in the firebase database */
public class BookModel implements Parcelable {
    private String bookId;
    private String bookTitle;
    private String bookAuthor;
    private String bookYear;
    private int bookType;
    private String bookSynopsis;
    private String bookContents;
    private String bookImage;
    private String bookPrice;
    private String wordCounts;

    public BookModel(String bookId, String bookTitle, String bookAuthor,
                     String bookYear, int bookType, String bookSynopsis,
                     String bookContents, String bookImage, String bookPrice, String wordCounts) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookYear = bookYear;
        this.bookType = bookType;
        this.bookSynopsis = bookSynopsis;
        this.bookContents = bookContents;
        this.bookImage = bookImage;
        this.bookPrice = bookPrice;
        this.wordCounts = wordCounts;
    }

    public BookModel() {
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookYear() {
        return bookYear;
    }

    public void setBookYear(String bookYear) {
        this.bookYear = bookYear;
    }

    public int getBookType() {
        return bookType;
    }

    public void setBookType(int bookType) {
        this.bookType = bookType;
    }

    public String getBookSynopsis() {
        return bookSynopsis;
    }

    public void setBookSynopsis(String bookSynopsis) {
        this.bookSynopsis = bookSynopsis;
    }

    public String getBookContents() {
        return bookContents;
    }

    public void setBookContents(String bookContents) {
        this.bookContents = bookContents;
    }

    public String getBookImage() {
        return bookImage;
    }

    public void setBookImage(String bookImage) {
        this.bookImage = bookImage;
    }

    public String getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(String bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getWordCounts() {
        return wordCounts;
    }

    public void setWordCounts(String wordCounts) {
        this.wordCounts = wordCounts;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.bookId);
        dest.writeString(this.bookTitle);
        dest.writeString(this.bookAuthor);
        dest.writeString(this.bookYear);
        dest.writeInt(this.bookType);
        dest.writeString(this.bookSynopsis);
        dest.writeString(this.bookContents);
        dest.writeString(this.bookImage);
        dest.writeString(this.bookPrice);
        dest.writeString(this.wordCounts);
    }

    protected BookModel(Parcel in) {
        this.bookId = in.readString();
        this.bookTitle = in.readString();
        this.bookAuthor = in.readString();
        this.bookYear = in.readString();
        this.bookType = in.readInt();
        this.bookSynopsis = in.readString();
        this.bookContents = in.readString();
        this.bookImage = in.readString();
        this.bookPrice = in.readString();
        this.wordCounts = in.readString();
    }

    public static final Creator<BookModel> CREATOR = new Creator<BookModel>() {
        @Override
        public BookModel createFromParcel(Parcel source) {
            return new BookModel(source);
        }

        @Override
        public BookModel[] newArray(int size) {
            return new BookModel[size];
        }
    };
}
