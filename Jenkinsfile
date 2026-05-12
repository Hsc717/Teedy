pipeline {
    agent any  // 使用任意可用的Jenkins代理节点
    stages {
        // 阶段1：清理之前的构建产物
        stage('Clean') {
            steps {
                sh 'mvn clean'
            }
        }
        // 阶段2：编译源代码
        stage('Compile') {
            steps {
                sh 'mvn compile'
            }
        }
        // 阶段3：执行单元测试（忽略测试失败继续构建，方便调试）
        stage('Test') {
            steps {
                sh 'mvn test -Dmaven.test.failure.ignore=true'
            }
        }
        // 阶段4：PMD代码静态检查
        stage('PMD') {
            steps {
                sh 'mvn pmd:pmd'
            }
        }
        // 阶段5：JaCoCo代码覆盖率报告
        stage('JaCoCo') {
            steps {
                sh 'mvn jacoco:report'
            }
        }
        // 阶段6：生成Javadoc API文档
        stage('Javadoc') {
            steps {
                sh 'mvn javadoc:javadoc'
            }
        }
        // 阶段7：生成Maven站点文档（整合所有报告）
        stage('Site') {
            steps {
                sh 'mvn site'
            }
        }
        // 阶段8：打包生成二进制制品（跳过已执行的测试）
        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }
    }
    // 构建后操作：无论成功失败都执行
    post {
        always {
            // 归档站点文档（HTML报告）
            archiveArtifacts artifacts: '**/target/site/**/*.*', fingerprint: true
            // 归档所有JAR和WAR二进制文件
            archiveArtifacts artifacts: '**/target/**/*.jar', fingerprint: true
            archiveArtifacts artifacts: '**/target/**/*.war', fingerprint: true
            // 解析并展示单元测试结果
            junit '**/target/surefire-reports/*.xml'
        }
    }
}