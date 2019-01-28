package com.mquinn.trainer.sl_extensions;

import org.opencv.core.TermCriteria;
import org.opencv.ml.SVM;

import static org.opencv.core.CvType.CV_32F;
import static org.opencv.core.CvType.CV_32S;
import static org.opencv.ml.Ml.ROW_SAMPLE;

public class SvmService {

    SVM svm;
    SvmTrainingData trainingData;

    public SvmService(){

        svm = SVM.create();

        svm.setType(SVM.C_SVC);
        svm.setKernel(SVM.LINEAR);
        svm.setTermCriteria(new TermCriteria(TermCriteria.MAX_ITER, 100, 1e-6));

    }

    public void finaliseSVM(SvmTrainingData inputTrainingData) {

        trainingData = inputTrainingData;

        trainingData.labels.convertTo(trainingData.labels, CV_32S);
        trainingData.samples.convertTo(trainingData.samples, CV_32F);

//        svm.train

        svm.train(trainingData.samples, ROW_SAMPLE, trainingData.labels);
//        svm.trainAuto(trainingData.samples, ROW_SAMPLE, trainingData.labels);

        svm.save("trained.xml");

    }

}
