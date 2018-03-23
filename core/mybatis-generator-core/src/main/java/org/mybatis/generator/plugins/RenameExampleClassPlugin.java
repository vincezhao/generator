/**
 *    Copyright 2006-2018 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.generator.plugins;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;
import static org.mybatis.generator.internal.util.messages.Messages.getString;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
 * This plugin demonstrates overriding the initialized() method to rename the
 * generated example classes. Instead of xxxExample, the classes will be named
 * xxxCriteria
 * 
 * This plugin accepts two properties:
 * <ul>
 * <li><tt>searchString</tt> (required) the regular expression of the name
 * search.</li>
 * <li><tt>replaceString</tt> (required) the replacement String.</li>
 * </ul>
 * 
 * For example, to change the name of the generated Example classes from
 * xxxExample to xxxCriteria, specify the following:
 * 
 * <dl>
 * <dt>searchString</dt>
 * <dd>Example$</dd>
 * <dt>replaceString</dt>
 * <dd>Criteria</dd>
 * </dl>
 * 
 * 
 * @author Jeff Butler
 * 
 */
public class RenameExampleClassPlugin extends PluginAdapter {
    private String searchString;
    private String replaceString;
    private Pattern pattern;

    /**
     * 
     */
    public RenameExampleClassPlugin() {
    }

    public boolean validate(List<String> warnings) {

        searchString = properties.getProperty("searchString"); //$NON-NLS-1$
        replaceString = properties.getProperty("replaceString"); //$NON-NLS-1$

        boolean valid = stringHasValue(searchString)
                && stringHasValue(replaceString);

        if (valid) {
            pattern = Pattern.compile(searchString);
        } else {
            if (!stringHasValue(searchString)) {
                warnings.add(getString("ValidationError.18", //$NON-NLS-1$
                        "RenameExampleClassPlugin", //$NON-NLS-1$
                        "searchString")); //$NON-NLS-1$
            }
            if (!stringHasValue(replaceString)) {
                warnings.add(getString("ValidationError.18", //$NON-NLS-1$
                        "RenameExampleClassPlugin", //$NON-NLS-1$
                        "replaceString")); //$NON-NLS-1$
            }
        }

        return valid;
    }

    @Override
    public void initialized(IntrospectedTable introspectedTable) {
        String oldType = introspectedTable.getExampleType();
        Matcher matcher = pattern.matcher(oldType);
        oldType = matcher.replaceAll(replaceString);

        introspectedTable.setExampleType(oldType);
    }

    @Override
    public boolean clientCountByExampleMethodGenerated(Method method, Interface interfaze,
                                                       IntrospectedTable introspectedTable) {
        return renameExample(method, introspectedTable);
    }

    @Override
    public boolean clientCountByExampleMethodGenerated(Method method, TopLevelClass topLevelClass,
                                                       IntrospectedTable introspectedTable) {
        return renameExample(method, introspectedTable);
    }

    @Override
    public boolean clientDeleteByExampleMethodGenerated(Method method, Interface interfaze,
                                                        IntrospectedTable introspectedTable) {
        return renameExample(method, introspectedTable);
    }

    @Override
    public boolean clientDeleteByExampleMethodGenerated(Method method, TopLevelClass topLevelClass,
                                                        IntrospectedTable introspectedTable) {
        return renameExample(method, introspectedTable);
    }

    @Override
    public boolean clientSelectByExampleWithBLOBsMethodGenerated(Method method, Interface interfaze,
                                                                 IntrospectedTable introspectedTable) {
        return renameExample(method, introspectedTable);
    }

    @Override
    public boolean clientSelectByExampleWithBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass,
                                                                 IntrospectedTable introspectedTable) {
        return renameExample(method, introspectedTable);
    }

    @Override
    public boolean clientSelectByExampleWithoutBLOBsMethodGenerated(Method method, Interface interfaze,
                                                                    IntrospectedTable introspectedTable) {
        return renameExample(method, introspectedTable);
    }

    @Override
    public boolean clientSelectByExampleWithoutBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass,
                                                                    IntrospectedTable introspectedTable) {
        return renameExample(method, introspectedTable);
    }

    @Override
    public boolean clientUpdateByExampleSelectiveMethodGenerated(Method method, Interface interfaze,
                                                                 IntrospectedTable introspectedTable) {
        return renameExample(method, introspectedTable);
    }

    @Override
    public boolean clientUpdateByExampleSelectiveMethodGenerated(Method method, TopLevelClass topLevelClass,
                                                                 IntrospectedTable introspectedTable) {
        return renameExample(method, introspectedTable);
    }

    @Override
    public boolean clientUpdateByExampleWithBLOBsMethodGenerated(Method method, Interface interfaze,
                                                                 IntrospectedTable introspectedTable) {
        return renameExample(method, introspectedTable);
    }

    @Override
    public boolean clientUpdateByExampleWithBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass,
                                                                 IntrospectedTable introspectedTable) {
        return renameExample(method, introspectedTable);
    }

    @Override
    public boolean clientUpdateByExampleWithoutBLOBsMethodGenerated(Method method, Interface interfaze,
                                                                    IntrospectedTable introspectedTable) {
        return renameExample(method, introspectedTable);
    }

    @Override
    public boolean clientUpdateByExampleWithoutBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass,
                                                                    IntrospectedTable introspectedTable) {
        return renameExample(method, introspectedTable);
    }

    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

        return renameModelExample(topLevelClass, introspectedTable);
    }

    @Override
    public boolean sqlMapCountByExampleElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {

        return renameXmlExample(element, introspectedTable);
    }

    @Override
    public boolean sqlMapDeleteByExampleElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return renameXmlExample(element, introspectedTable);
    }

    @Override
    public boolean sqlMapExampleWhereClauseElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return renameXmlExample(element, introspectedTable);
    }

    @Override
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element,
                                                                     IntrospectedTable introspectedTable) {
        return renameXmlExample(element, introspectedTable);
    }

    @Override
    public boolean sqlMapSelectByExampleWithBLOBsElementGenerated(XmlElement element,
                                                                  IntrospectedTable introspectedTable) {
        return renameXmlExample(element, introspectedTable);
    }

    @Override
    public boolean sqlMapUpdateByExampleSelectiveElementGenerated(XmlElement element,
                                                                  IntrospectedTable introspectedTable) {
        return renameXmlExample(element, introspectedTable);
    }

    @Override
    public boolean sqlMapUpdateByExampleWithBLOBsElementGenerated(XmlElement element,
                                                                  IntrospectedTable introspectedTable) {
        return renameXmlExample(element, introspectedTable);
    }

    @Override
    public boolean sqlMapUpdateByExampleWithoutBLOBsElementGenerated(XmlElement element,
                                                                     IntrospectedTable introspectedTable) {
        return renameXmlExample(element, introspectedTable);
    }

    @Override
    public boolean providerCountByExampleMethodGenerated(Method method, TopLevelClass topLevelClass,
                                                         IntrospectedTable introspectedTable) {
        return renameExample(method, introspectedTable);
    }

    @Override
    public boolean providerDeleteByExampleMethodGenerated(Method method, TopLevelClass topLevelClass,
                                                          IntrospectedTable introspectedTable) {
        return renameExample(method, introspectedTable);
    }

    @Override
    public boolean providerSelectByExampleWithBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass,
                                                                   IntrospectedTable introspectedTable) {
        return renameExample(method, introspectedTable);
    }

    @Override
    public boolean providerSelectByExampleWithoutBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass,
                                                                      IntrospectedTable introspectedTable) {
        return renameExample(method, introspectedTable);
    }

    @Override
    public boolean providerUpdateByExampleSelectiveMethodGenerated(Method method, TopLevelClass topLevelClass,
                                                                   IntrospectedTable introspectedTable) {
        return renameExample(method, introspectedTable);
    }

    @Override
    public boolean providerUpdateByExampleWithBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass,
                                                                   IntrospectedTable introspectedTable) {
        return renameExample(method, introspectedTable);
    }

    @Override
    public boolean providerUpdateByExampleWithoutBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass,
                                                                      IntrospectedTable introspectedTable) {
        return renameExample(method, introspectedTable);
    }

    @Override
    public boolean providerApplyWhereMethodGenerated(Method method, TopLevelClass topLevelClass,
                                                     IntrospectedTable introspectedTable) {
        renameExample(method, introspectedTable);

        return renameModelExample(topLevelClass, introspectedTable);
    }

    /**
     * 修改模型类里的example
     * @param topLevelClass
     * @param introspectedTable
     * @return
     */
    private boolean renameModelExample(TopLevelClass topLevelClass, IntrospectedTable introspectedTable){
        String paramName = introspectedTable.getExampleType();
        String converted = convertWord(getClassBaseName(paramName));

        /** 类的所有方法 */
        List<Method> methods = topLevelClass.getMethods();
        List<Method> newMethods = new ArrayList<Method>();
        for (Method method : methods) {
            /** 将方法体里的example替换 */
            List<String> lines = method.getBodyLines();
            List<String> newLines = new ArrayList<String>();
            for (String line : lines) {
                newLines.add(line.replaceAll("example", converted));
            }
            method.getBodyLines().clear();
            method.getBodyLines().addAll(newLines);

            /** 将参数里的example替换 */
            List<Parameter> parameters = method.getParameters();
            List<Parameter> newParameters = new ArrayList<Parameter>();
            for (Parameter parameter : parameters) {
                String parameterName = parameter.getName();
                if ("example".equals(parameterName)) {
                    String shortName = parameter.getType().getShortName();
                    String convertedWord = convertWord(shortName);
                    Parameter newParameter = new Parameter(parameter.getType(), convertedWord,false);
                    /** 将参数注解里的example替换 */
                    List<String> annotations = parameter.getAnnotations();
                    for (String anno : annotations) {
                        newParameter.addAnnotation(anno.replace("example", convertedWord));
                    }
                    newParameters.add(newParameter);
                } else {
                    newParameters.add(parameter);
                }
            }
            method.getParameters().clear();
            method.getParameters().addAll(newParameters);
            newMethods.add(method);
        }
        topLevelClass.getMethods().clear();
        topLevelClass.getMethods().addAll(newMethods);
        return true;
    }

    /**
     * 替换XML里的example和Example
     * @param element
     * @param introspectedTable
     * @return
     */
    private boolean renameXmlExample(XmlElement element, IntrospectedTable introspectedTable){
        String paramName = introspectedTable.getExampleType();
        paramName = getClassBaseName(paramName);
        renameXmlExampleRecursive(element, paramName);

        return true;
    }

    /**
     * 对于XML里的example和Example进行递归替换
     * @param element
     * @param paramName
     */
    private void renameXmlExampleRecursive(XmlElement element, String paramName){
        String convertedWord = convertWord(paramName);

        List<Attribute> attributeList = element.getAttributes();
        List<Attribute> newAttributeList = new ArrayList<Attribute>();
        for (Attribute attr : attributeList) {
            String name = attr.getName();
            String value = attr.getValue();
            value = value.replaceAll("example", convertedWord);
            Attribute newAttr = new Attribute(name,value.replaceAll("Example", paramName));
            newAttributeList.add(newAttr);
        }
        element.getAttributes().clear();
        element.getAttributes().addAll(newAttributeList);

        List<Element> elementList = element.getElements();
        List<Element> newElementList = new ArrayList<Element>();
        for (Element e : elementList) {
            if (e instanceof TextElement) {
                String content = ((TextElement)e).getContent();
                content = content.replaceAll("example", convertedWord);
                Element newElement = new TextElement(content.replaceAll("Example", paramName));
                newElementList.add(newElement);
            } else if (e instanceof XmlElement) {
                /** 递归 */
                renameXmlExampleRecursive((XmlElement) e, paramName);
                newElementList.add(e);
            }
        }
        element.getElements().clear();
        element.getElements().addAll(newElementList);
    }

    /**
     *
     * @param method
     * @param introspectedTable
     * @return
     */
    private boolean renameExample(Method method, IntrospectedTable introspectedTable){

        /** 将方法参数里的example变量名替换 */
        List<Parameter> parameters = method.getParameters();
        List<Parameter> newParameters = new ArrayList<Parameter>();
        for (Parameter parameter : parameters) {
            String parameterName = parameter.getName();
            if ("example".equals(parameterName)) {
                String shortName = parameter.getType().getShortName();
                String convertedWord = convertWord(shortName);
                Parameter newParameter = new Parameter(parameter.getType(), convertedWord, false);
                /** 将参数注解里的example别名替换 */
                List<String> annotations = parameter.getAnnotations();
                for (String anno : annotations) {
                    newParameter.addAnnotation(anno.replaceAll("example", convertedWord));
                }
                newParameters.add(newParameter);
            } else {
                newParameters.add(parameter);
            }
        }
        method.getParameters().clear();
        method.getParameters().addAll(newParameters);

        /** 将方法名里的Example替换 */
        String methodName = method.getName();
        String example = introspectedTable.getExampleType();
        String classBaseName = getClassBaseName(example);
        String convertedWord = convertWord(classBaseName);
        String newMethodName = methodName.replaceAll("Example", classBaseName);
        method.setName(newMethodName);

        /** 将方法体里的example变量名替换 */
        List<String> bodyLines = method.getBodyLines();
        List<String> newBodyLines = new ArrayList<String>();
        for (String line : bodyLines) {
            String newLine = line.replaceAll("example", convertedWord);
            newLine = newLine.replaceAll(methodName, newMethodName);
            newBodyLines.add(newLine);
        }
        method.getBodyLines().clear();
        method.getBodyLines().addAll(newBodyLines);

        /** 将方法注解里的Example替换，用于SQL采用注解方式 */
        List<String> annotations = method.getAnnotations();
        List<String> newAnnotations = new ArrayList<String>();
        for (String anno : annotations) {
            newAnnotations.add(anno.replaceAll("Example", classBaseName));
        }
        method.getAnnotations().clear();
        method.getAnnotations().addAll(newAnnotations);

        return true;
    }

    /** 将首字母小写 */
    private String convertWord(String oldWord){
        if (null == oldWord || oldWord.length() < 1) {
            return oldWord;
        }
        StringBuilder sb = new StringBuilder();
        String first = oldWord.substring(0,1).toLowerCase();
        String last = oldWord.substring(1,oldWord.length());
        sb.append(first);
        sb.append(last);
        return sb.toString();
    }

    /** 根据类全名获取类名 */
    private String getClassBaseName(String classType){
        String[] names = classType.split("[.]");
        return names[names.length - 1];
    }

}
