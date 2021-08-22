package com.ssm.dao;

import com.ssm.pojo.Doc;

import java.util.List;

public interface DocMapper {

    // add
    int addDoc(Doc doc);

    // delete
    int deleteDocByID(int docID);

    // update
    int updateDoc(Doc doc);

    // query
    Doc queryDocByID(int docID);

    // query all doc
    List<Doc> queryAllDoc();
}
