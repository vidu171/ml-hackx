import pandas as pd
import numpy as np

import matplotlib.pyplot as plt
import seaborn as sns

dataset = pd.read_csv('data/Bus Route Trip Data.csv')
data_new = dataset.dropna()

# Dropping Identifiers
data_new.drop(['RouteName','VehicleNo','Operation date','Sch Trip Start time','Actual Trip Start time','Sch Trip End time','Actual Trip End time','ScheduledKm','ActualDistance'], axis=1, inplace=True)


places = data_new['RouteName'].values.tolist()
unique_places = set(places)
print(unique_places)
places = list(unique_places)

# 569 unique values

# Loading E-Ticket Data
e_ticket_dataset = pd.read_excel('data/Passenger e-ticket data/Report_01_JUL_2018.xlsx')
e_ticket_dataset.isnull().sum()

# taking 20000 only from the given dataset's
e_ticket_dataset = e_ticket_dataset.iloc[:20000,:]
data_new = data_new.iloc[:20000,:]
data_new['Start'] = e_ticket_dataset['Origin Bus Stop Name']
data_new['End'] = e_ticket_dataset['Destination Bus Stop Name']

# There is no NaN in the dataset
users = e_ticket_dataset['User Count'].values.tolist()
unique_count = set(users)
unique_count = list(unique_count)

# Creating a Price per Head Column
e_ticket_dataset['Price per Head'] = e_ticket_dataset['Amount']/e_ticket_dataset['User Count']

# Dropping Amount and User Count
e_ticket_dataset.drop(['Amount', 'User Count'], axis=1, inplace=True)

# Count of RouteNames
places = e_ticket_dataset['Routename'].values.tolist()
unique_places = set(places)
print(unique_places)
places = list(unique_places)

# Filling up two new columns
data_new['comfort_level'] = np.random.randint(1, 5, data_new.shape[0])
data_new['overall_experience'] = np.random.randint(1, 5, data_new.shape[0])

# Dumping to a new Dataset CSV
data_new.to_csv('data/data_new.csv')
import csv,json
json_api_data = json.dumps(list(csv.reader(open('data/data_new.csv'))))

np.savetxt("JSON_FINAL_DATA.txt", json_api_data, fmt="%s")

text_file = open("JSON_DATA_FINAL.txt", "w")
text_file.write(json_api_data)
text_file.close()