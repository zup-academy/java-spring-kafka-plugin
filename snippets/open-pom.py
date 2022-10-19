import xml.etree.ElementTree as ET
from templateframework.metadata import Metadata

def run(metadata: Metadata = None):
    tree = ET.parse('pom.xml')
    root = tree.getroot()
    
    for xml in root:
        if "groupId" in xml.tag:
            group = xml.tag
          
        if "artifactId" in xml.tag:
            artifact = xml.tag
            
        if "name" in xml.tag:
            name = xml.tag
            


    ## group_id -> caso tenha o caracter "-" deverá ser substituido por ""
    group_id = root.find(group)
   

    ## artifactory_id -> caso tenha o caracter "-" deverá ser substituido por ""
    artifact_id = root.find(artifact)
    
    if "-" in artifact_id.text:
        artifact_id_text = artifact_id.text.replace("-","")
    else:  
        artifact_id_text = artifact_id.text 

    ## project name -> caso tenha o caracter "-" deverá ser substituido por "",
    ## caso tenha a primeira letra minuscula devera ser sub por maiscula
    ## exemplo casa-do-codigo -> CasaDoCodigo
    input_name = root.find(name)

    if "-" in input_name.text:
        input_name_text = input_name.text.replace("-"," ")
        input_name_text = input_name_text.title()
        input_name_text = input_name_text.replace(" ","")
    else:
        input_name_text = input_name.text
        input_name_text = input_name_text.title()

    application_package = f"src.main.java.{group_id.text}.{artifact_id_text}.{input_name_text}"
    
    if "Application"  not in input_name_text:
       application_package = f"{application_package}Application"
    

   
    directory_path = f"src.main.java.{group_id.text}.{artifact_id_text}"
    directory_test_path = f"src.test.java.{group_id.text}.{artifact_id_text}"
    
    metadata.inputs['application_path_default'] = application_package
    application_class_full_path = application_package.replace(".","/")
    metadata.inputs['application_class_full_path'] = f"{application_class_full_path}.java"
    metadata.inputs['directory_path_code'] =f"{group_id.text}.{artifact_id_text}"
    metadata.inputs['directory_path'] = directory_path
    metadata.inputs['directory_test_path'] = directory_test_path
    
    return metadata