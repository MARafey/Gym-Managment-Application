package com.example.demo2;

import java.sql.Date;

public class Membership {
    public int membershipID;
    public int userID;
    public String membershipStatus;
    public Date membershipStartDate;
    public Date membershipEndDate;
    public String membershipTier;

    public Membership(int membershipID, int userID, String membershipStatus, Date membershipStartDate, Date membershipEndDate, String membershipTier) {
        this.membershipID = membershipID;
        this.userID = userID;
        this.membershipStatus = membershipStatus;
        this.membershipStartDate = membershipStartDate;
        this.membershipEndDate = membershipEndDate;
        this.membershipTier = membershipTier;
    }

    public int getMembershipID() {
        return membershipID;
    }

    public void setMembershipID(int membershipID) {
        this.membershipID = membershipID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        userID = userID;
    }

    public String getMembershipStatus() {
        return membershipStatus;
    }

    public void setMembershipStatus(String membershipStatus) {
        membershipStatus = membershipStatus;
    }

    public Date getMembershipStartDate() {
        return membershipStartDate;
    }

    public void setMembershipStartDate(Date membershipStartDate) {
        membershipStartDate = membershipStartDate;
    }

    public Date getMembershipEndDate() {
        return membershipEndDate;
    }

    public void setMembershipEndDate(Date membershipEndDate) {
        membershipEndDate = membershipEndDate;
    }

    public String getMembershipTier() {
        return membershipTier;
    }

    public void setMembershipTier(String membershipTier) {
        membershipTier = membershipTier;
    }
}
