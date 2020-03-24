package com.wyp.utils;


import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: wyp
 * @date: 2020/2/1
 * @description: 参数转化
 */
public class ConvertBeanUtil {
    /**
     * @param inputObject input object (source)
     * @param outputClz   output Class(target)
     * @param <I>
     * @param <O>
     * @return
     */
    public static <I, O> O convertToBean(I inputObject, Class<O> outputClz) {
        return convertToBean(inputObject, outputClz, null);
    }

    /**
     * <p>
     * Converter use scenes : need custom case
     * Convert logic :  Execute the converter after all field convert completed
     * </p>
     *
     * @param inputObject input object (source)
     * @param outputClz   output Class (target)
     * @param converter   custom Converter
     * @param <I>
     * @param <O>
     * @return
     */
    public static <I, O> O convertToBean(I inputObject, Class<O> outputClz, Converter converter) {
        return convertToBean0(inputObject, outputClz, converter);
    }

    /**
     * <p>
     * serve between {@code List} transfer
     * </p>
     *
     * @param inputList
     * @param outputClz
     * @param <I>
     * @param <O>
     * @return
     */
    public static <I, O> List<O> convertToList(List<I> inputList, Class<O> outputClz) {
        return convertToList0(inputList, outputClz, null);
    }

    /**
     * <p>
     * serve between {@code List} transfer, Support custom converter
     * </p>
     *
     * @param inputList
     * @param outputClz
     * @param converter
     * @param <I>
     * @param <O>
     * @return
     */
    public static <I, O> List<O> convertToList(List<I> inputList, Class<O> outputClz, Converter converter) {
        return convertToList0(inputList, outputClz, converter);
    }


    //base convert list
    private static <I, O> List<O> convertToList0(List<I> inputList, Class<O> outputClz, Converter converter) {

        if (CollectionUtils.isEmpty(inputList)) {
            return Collections.EMPTY_LIST;
        }

        List<O> outputList = new ArrayList();
        for (I inputObject : inputList) {
            O o = convertToBean0(inputObject, outputClz, converter);
            outputList.add(o);
        }
        return outputList;
    }

    //base convert bean
    private static <I, O> O convertToBean0(I inputObject, Class<O> outputClz, Converter convert) {
        try {

            if (null == inputObject) {
                return null;
            }

            O outputObject = outputClz.newInstance();
            BeanUtils.copyProperties(inputObject, outputObject);
            if (null != convert) {
                convert.convert(inputObject, outputObject);
            }
            return outputObject;
        } catch (Exception e) {
            throw new RuntimeException("[ConvertBeanUtil.convertToBean] Bean convert error");
        }
    }
}
