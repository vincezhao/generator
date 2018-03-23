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

import java.util.List;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

/**
 * Created by lingfeng.zw on 17/5/24.
 */
public class SplitTablePlugin extends PluginAdapter {

    private String pattern;

    @Override
    public boolean validate(List<String> warnings) {
        pattern = properties.getProperty("pattern");
        return stringHasValue(pattern);
    }

    @Override
    public void initialized(IntrospectedTable introspectedTable) {
        if (null == pattern) {
            return;
        }
        String tableName = introspectedTable.getFullyQualifiedTable().getIntrospectedTableName();
        String newTableName = tableName.replaceFirst(pattern,"");
        introspectedTable.getFullyQualifiedTable().renameTableName(newTableName);
        introspectedTable.getTableConfiguration().setTableName(newTableName);
        introspectedTable.setSqlMapAliasedFullyQualifiedRuntimeTableName(newTableName);
        introspectedTable.setSqlMapFullyQualifiedRuntimeTableName(newTableName);
        introspectedTable.setIbatis2SqlMapNamespace(newTableName);
        introspectedTable.setIbatis2SqlMapFileName(newTableName + "_SqlMap.xml");
    }
}
