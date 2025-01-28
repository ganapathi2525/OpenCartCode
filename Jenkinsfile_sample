pipeline{
    
    agent any
    stages{
        
        stage("Build"){
            steps{
                echo("Build the project")
            }
        }
        stage("Deploy to DEV"){
            steps{
                echo("Deploy to dev Environment")
            }
        }
        stage("Deploy to QA"){
            steps{
                echo("Deploy to QA Environment")
            }
        }
        stage("Run Regression automation test cases"){
            steps{
                echo("Run Regression automation test cases")
            }
        }
         stage("Deploy to Stage"){
            steps{
                echo("Deploy to Stage Environment")
            }
        }
        stage("Run sanity automation test cases"){
            steps{
                echo("Run sanity automation test cases")
            }
        }
        
         stage("Deploy to Prod"){
            steps{
                echo("Deploy to Prod Environment")
            }
        }
        
        
        
    }
    
    
}