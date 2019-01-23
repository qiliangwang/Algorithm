---
title: Hello World
date: 2018-09-15 13:42:09
---
Today is 2018/9/16,  Hello World.

## Having Fun

Logger: 2018/9/21

selenium

```python
from selenium import webdriver
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions as EC
import shutil
import os
import pandas as pd


def download_exports_imports(country, browser):

    # browser = webdriver.Chrome()
    #
    # browser.get('https://wits.worldbank.org/countrystats.aspx?lang=en')

    wait = WebDriverWait(browser, 10)
    print('download_exports', country)
    country.click()

    exports = wait.until(EC.element_to_be_clickable((By.ID, 'lblExport')))

    exports.click()

    # 然后更改年份和TradeFlow
    select_year = wait.until(EC.element_to_be_clickable((By.ID, 'selectedYear')))
    select_year.click()

    by_indicator = wait.until(
        EC.element_to_be_clickable((By.XPATH, '//*[@id="lblByIndicator"]')))
    by_indicator.click()

    start_input = browser.find_element(By.CSS_SELECTOR, '#byIndicator_year_Strartdropdown > input[type="hidden"]'
                                       ).get_attribute('value')
    end_input = browser.find_element(By.CSS_SELECTOR,
                                       '#byIndicator_year_Enddropdown > input[type="hidden"]').get_attribute('value')

    years = int(end_input) - int(start_input)

    by_country = wait.until(
        EC.element_to_be_clickable((By.XPATH, '//*[@id="lblByCounty"]')))
    by_country.click()

    all_year = wait.until(EC.element_to_be_clickable((By.ID, 'dropdownlistContentbyCountry_year_dropdown')))
    all_year.click()
    loop_year = min(8, years)
    # print(loop_year)
    year_condition_one = 'listitem'
    year_condition_two = 'innerListBoxbyCountry_year_dropdown'
    for i in range(loop_year):
        year_selector = year_condition_one + str(i) + year_condition_two
        new_year = browser.find_element(By.ID, year_selector)
        new_year.click()
        update = browser.find_element(By.XPATH, '//*[@id="lblUpdate"]')
        update.click()

        # download extra out later
        show_more = wait.until(EC.element_to_be_clickable((By.ID, 'showHideLink')))
        show_more.click()
        share_in_total = wait.until(EC.element_to_be_clickable((By.ID, 'listitem1jqxlistbox')))
        share_in_total.click()
        # print(exports, imports)
        data_download = wait.until(EC.element_to_be_clickable((By.ID, 'DataDownload')))
        data_download.click()

        '//*[@id="dropDownFileFormat"]/li/a'
        '#dropDownFileFormat > li > a //*[@id="dropDownFileFormat"]/li/a CSS_SELECTOR'

        excel = wait.until(EC.element_to_be_clickable((By.CSS_SELECTOR, '#dropDownFileFormat > li > a')))
        excel.click()

        # download successful
        select_year = wait.until(EC.element_to_be_clickable((By.ID, 'selectedYear')))
        select_year.click()
        # download import //*[@id="listitem2innerListBoxbyCountry_tradeFlow_dropdown"]/span
        trade_flow = wait.until(EC.element_to_be_clickable((By.ID, 'dropdownlistContentbyCountry_tradeFlow_dropdown')))
        trade_flow.click()
        trade_import = browser.find_element(By.XPATH, '//*[@id="listitem2innerListBoxbyCountry_tradeFlow_dropdown"]/span')
        trade_import.click()
        update = browser.find_element(By.XPATH, '//*[@id="lblUpdate"]')
        update.click()
        # download extra out later
        show_more = wait.until(EC.element_to_be_clickable((By.ID, 'showHideLink')))
        show_more.click()
        '//*[@id="listitem2jqxlistbox"]/span  #listitem2jqxlistbox > span'
        share_in_total = wait.until(EC.element_to_be_clickable((By.XPATH, '//*[@id="listitem2jqxlistbox"]/span')))
        share_in_total.click()
        # print(exports, imports)
        data_download = wait.until(EC.element_to_be_clickable((By.ID, 'DataDownload')))
        data_download.click()

        '//*[@id="dropDownFileFormat"]/li/a'
        '#dropDownFileFormat > li > a //*[@id="dropDownFileFormat"]/li/a CSS_SELECTOR'

        excel = wait.until(EC.element_to_be_clickable((By.CSS_SELECTOR, '#dropDownFileFormat > li > a')))
        excel.click()
        # download
        select_year = wait.until(EC.element_to_be_clickable((By.ID, 'selectedYear')))
        select_year.click()

        all_year = wait.until(EC.element_to_be_clickable((By.ID, 'dropdownlistContentbyCountry_year_dropdown')))
        all_year.click()
    # browser.close()
    pass


def main():
    for i in range(198):
        browser = webdriver.Chrome()
        try:
            browser.get('https://wits.worldbank.org/countrystats.aspx?lang=en')

            countries = browser.find_elements(By.CLASS_NAME, 'countryHeading')

            country = countries[8 + i]

            download_exports_imports(country, browser)
            browser.close()
        except:
            browser.close()
            with open('error.txt', 'a+') as f:
                f.write(str(i) + '\n')
        max_count = cal_max()
        print(max_count)
        move_file(max_count)


def move_file(count):
    download_dir = 'F:/Users/Vader/Downloads/'
    files = os.listdir(download_dir)
    excel = [os.path.join(download_dir, file) for file in files if file.endswith('.xlsx')]
    for signal_excel in excel:
        count = count + 1
        cache_dir = os.path.join('./cache', 'Download_' + str(count) + '.xlsx')
        shutil.copy(signal_excel, cache_dir)
        os.unlink(signal_excel)
    pass


def cal_max():
    file_list = os.listdir('./cache')
    numbers = [int(signal_file.split('.xlsx')[0].split('_')[1]) for signal_file in file_list]
    if len(numbers) == 0:
        return 0
    else:
        return max(numbers)


if __name__ == '__main__':
    main()
```

Logger: 2018/9/24

```powershell
pip install virtualenv
pip install -i https://pypi.doubanio.com/simple virtualenv 

pip install -i https://pypi.doubanio.com/simple virtualenvwrapper-win

mkvirtualenv -p F:\ProgramData\Anaconda3\python.exe VueShop


pip install -i https://pypi.doubanio.com/simple djangorestframework

pip install -i https://pypi.doubanio.com/simple django

pip install markdownpip 

pip install django filter

workon VueShop

pip install -i https://pypi.doubanio.com/simple mysqlclient
```

改了下之前的code完善了一些功能把吧~，虽说是having fun的code（就是写不考虑后果），但还是完善下比较合适。

```python
from selenium import webdriver
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions as EC
import shutil
import os
import pandas as pd


def download_exports_imports(country, browser):

    wait = WebDriverWait(browser, 10)
    country.click()

    exports = wait.until(EC.element_to_be_clickable((By.ID, 'lblExport')))

    exports.click()

    # 然后更改年份和TradeFlow
    select_year = wait.until(EC.element_to_be_clickable((By.ID, 'selectedYear')))
    select_year.click()

    by_indicator = wait.until(
        EC.element_to_be_clickable((By.XPATH, '//*[@id="lblByIndicator"]')))
    by_indicator.click()

    start_input = browser.find_element(By.CSS_SELECTOR, '#byIndicator_year_Strartdropdown > input[type="hidden"]'
                                       ).get_attribute('value')
    end_input = browser.find_element(By.CSS_SELECTOR,
                                       '#byIndicator_year_Enddropdown > input[type="hidden"]').get_attribute('value')

    years = int(end_input) - int(start_input)

    by_country = wait.until(
        EC.element_to_be_clickable((By.XPATH, '//*[@id="lblByCounty"]')))
    by_country.click()

    all_year = wait.until(EC.element_to_be_clickable((By.ID, 'dropdownlistContentbyCountry_year_dropdown')))
    all_year.click()
    loop_year = min(8, years)
    
    year_condition_one = 'listitem'
    year_condition_two = 'innerListBoxbyCountry_year_dropdown'
    for i in range(loop_year):
        
        year_selector = year_condition_one + str(i) + year_condition_two
        new_year = browser.find_element(By.ID, year_selector)
        new_year.click()
        trade_flow = wait.until(EC.element_to_be_clickable((By.ID, 'dropdownlistContentbyCountry_tradeFlow_dropdown')))
        trade_flow.click()
        trade_export = browser.find_element(By.XPATH,
                                            '//*[@id="listitem1innerListBoxbyCountry_tradeFlow_dropdown"]/span')
        trade_export.click()

        update = browser.find_element(By.XPATH, '//*[@id="lblUpdate"]')
        update.click()

        # download extra out later
        show_more = wait.until(EC.element_to_be_clickable((By.ID, 'showHideLink')))
        show_more.click()
        share_in_total = wait.until(EC.element_to_be_clickable((By.ID, 'listitem1jqxlistbox')))
        share_in_total.click()

        data_download = wait.until(EC.element_to_be_clickable((By.ID, 'DataDownload')))
        data_download.click()

        excel = wait.until(EC.element_to_be_clickable((By.CSS_SELECTOR, '#dropDownFileFormat > li > a')))
        excel.click()
        # download successful

        select_year = wait.until(EC.element_to_be_clickable((By.ID, 'selectedYear')))
        select_year.click()
        # download import //*[@id="listitem2innerListBoxbyCountry_tradeFlow_dropdown"]/span
        trade_flow = wait.until(EC.element_to_be_clickable((By.ID, 'dropdownlistContentbyCountry_tradeFlow_dropdown')))
        trade_flow.click()
        trade_import = browser.find_element(By.XPATH, '//*[@id="listitem2innerListBoxbyCountry_tradeFlow_dropdown"]/span')
        trade_import.click()
        update = browser.find_element(By.XPATH, '//*[@id="lblUpdate"]')
        update.click()

        # download extra out later
        show_more = wait.until(EC.element_to_be_clickable((By.ID, 'showHideLink')))
        show_more.click()
        '//*[@id="listitem2jqxlistbox"]/span  #listitem2jqxlistbox > span'
        share_in_total = wait.until(EC.element_to_be_clickable((By.XPATH, '//*[@id="listitem2jqxlistbox"]/span')))
        share_in_total.click()
        data_download = wait.until(EC.element_to_be_clickable((By.ID, 'DataDownload')))
        data_download.click()
        '//*[@id="dropDownFileFormat"]/li/a'
        '#dropDownFileFormat > li > a //*[@id="dropDownFileFormat"]/li/a CSS_SELECTOR'
        excel = wait.until(EC.element_to_be_clickable((By.CSS_SELECTOR, '#dropDownFileFormat > li > a')))
        excel.click()
        # download

        select_year = wait.until(EC.element_to_be_clickable((By.ID, 'selectedYear')))
        select_year.click()

        all_year = wait.until(EC.element_to_be_clickable((By.ID, 'dropdownlistContentbyCountry_year_dropdown')))
        all_year.click()
    # browser.close()
    pass


def main():
    error_index = read_logs()
    for i in error_index:
        browser = webdriver.Chrome()
        try:
            browser.get('https://wits.worldbank.org/countrystats.aspx?lang=en')

            countries = browser.find_elements(By.CLASS_NAME, 'countryHeading')

            country = countries[8 + i]

            download_exports_imports(country, browser)
            browser.close()
        except:
            browser.close()
            with open('error.txt', 'a+') as f:
                f.write(str(i) + '\n')
        max_count = cal_max()
        print(max_count)
        move_file(max_count)


def move_file(count):
    download_dir = 'F:/Users/Vader/Downloads/'
    files = os.listdir(download_dir)
    excel = [os.path.join(download_dir, file) for file in files if file.endswith('.xlsx')]
    for signal_excel in excel:
        count = count + 1
        cache_dir = os.path.join('./cache', 'Download_' + str(count) + '.xlsx')
        shutil.copy(signal_excel, cache_dir)
        os.unlink(signal_excel)
    pass


def cal_max():
    file_list = os.listdir('./cache')
    numbers = [int(signal_file.split('.xlsx')[0].split('_')[1]) for signal_file in file_list]
    if len(numbers) == 0:
        return 0
    else:
        return max(numbers)


def read_logs():
    with open('error.txt') as f:
        lines = f.readlines()
    error_index = [int(line.split('\n')[0]) for line in lines]
    return error_index


def new_excel_name(file):
    excel = pd.ExcelFile(file)
    df = excel.parse('Partner')
    new_name = df['Reporter Name'].values[0] + '_' + df['Trade Flow'].values[0] + '_' + str(df['Year'].values[0]) + '.xlsx'
    
    return new_name


def rename_excel():
    base_dir = './cache'
    file_list = os.listdir(base_dir)
    files = [os.path.join(base_dir, f) for f in file_list if f.startswith('Download')]
    for file in files:
        new_dir = os.path.join(base_dir, new_excel_name(file))
        try:
            os.rename(file, new_dir)
        except:
            print(new_dir)
    pass


if __name__ == '__main__':
    main()
```

## TODO

Logger : 2018/9/23.

1 Docker && K8s && OpenStack

2 Spring boot && Spring Cloud && Netty && React

3 Nginx && ELK

4 Hadoop && HBase && Spark && Kafka && Zookeeper && Storm && Spark Streaming

5 Machine Learning && Deep Learning

好了慢慢来。

