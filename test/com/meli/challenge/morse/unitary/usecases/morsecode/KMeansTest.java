package com.meli.challenge.morse.unitary.usecases.morsecode;

import com.meli.challenge.morse.Commons;
import com.meli.challenge.morse.service.MorseCodeService;
import com.meli.challenge.morse.usecases.morsecode.MorseCodeTranslater;
import com.meli.challenge.morse.usecases.morsecode.kmeans.KMeans;
import com.meli.challenge.morse.usecases.morsecode.kmeans.KMeansCluster;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

@RunWith(MockitoJUnitRunner.class)
public class KMeansTest extends Commons {

    private KMeans kMeans;

    @Test
    public void makeGroupingTest() {
        kMeans = new KMeans(zeros, ones);
        Assert.assertNotNull(kMeans);

        kMeans.makeGrouping();
        Assert.assertNotNull(kMeans.getTimeUnit(0));
    }

}
