package com.ssm.service;

import com.ssm.dao.DocMapper;
import com.ssm.pojo.Doc;

import java.util.List;

import static com.ssm.utils.Util.getSystemTime;

public class DocServiceImpl implements DocService {

    private DocMapper docMapper;

    public void setDocMapper(DocMapper docMapper) {
        this.docMapper = docMapper;
    }

    public int addDoc(Doc doc) {
        doc.setDate(getSystemTime());
        return docMapper.addDoc(doc);
    }

    public int deleteDocByID(int docID) {
        return docMapper.deleteDocByID(docID);
    }

    public int updateDoc(Doc doc) {
        return docMapper.updateDoc(doc);
    }

    public Doc queryDocByID(int docID) {
        return docMapper.queryDocByID(docID);
    }

    public List<Doc> queryAllDoc() {
        return docMapper.queryAllDoc();
    }

}
