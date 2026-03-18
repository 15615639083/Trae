import { createContext, useState, useContext } from 'react';

// 创建上下文
const AppContext = createContext();

// 初始数据
const initialData = {
  softwareLinks: [
    {
      id: 1,
      name: '软件目录',
      url: '#',
      description: '软件下载目录'
    }
  ],
  gameLinks: [
    {
      id: 1,
      name: '游戏目录',
      url: '#',
      description: '游戏下载目录'
    }
  ],
  navigationLinks: [
    { id: 1, name: '首页', url: '/' },
    { id: 2, name: '软件', url: '#software' },
    { id: 3, name: '游戏', url: '#games' },
    { id: 4, name: '搜索', url: '#search' },
    { id: 5, name: '反馈', url: '#feedback' }
  ]
};

// 上下文提供者组件
export const AppProvider = ({ children }) => {
  const [data, setData] = useState(initialData);

  // 更新软件链接
  const updateSoftwareLinks = (newLinks) => {
    setData(prev => ({ ...prev, softwareLinks: newLinks }));
  };

  // 更新游戏链接
  const updateGameLinks = (newLinks) => {
    setData(prev => ({ ...prev, gameLinks: newLinks }));
  };

  // 更新导航链接
  const updateNavigationLinks = (newLinks) => {
    setData(prev => ({ ...prev, navigationLinks: newLinks }));
  };

  return (
    <AppContext.Provider
      value={{
        data,
        updateSoftwareLinks,
        updateGameLinks,
        updateNavigationLinks
      }}
    >
      {children}
    </AppContext.Provider>
  );
};

// 自定义钩子，方便使用上下文
export const useAppContext = () => {
  const context = useContext(AppContext);
  if (!context) {
    throw new Error('useAppContext must be used within an AppProvider');
  }
  return context;
};
